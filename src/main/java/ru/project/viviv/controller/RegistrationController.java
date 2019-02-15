package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.project.viviv.model.dto.UserDTO;
import ru.project.viviv.model.entity.OnRegistrationCompleteEvent;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.entity.VerificationToken;
import ru.project.viviv.model.service.UserService;
import ru.project.viviv.validation.EmailExistsException;
import ru.project.viviv.validation.UsernameExistsException;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Qualifier("messageSource")
    @Autowired
    private MessageSource messages;

    @GetMapping(value = "/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDTO accountDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e1) {
            result.rejectValue("email", "message.regError");
        } catch (UsernameExistsException e1) {
            result.rejectValue("username", "message.regUsernameError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        try {
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered));
        } catch (Exception me) {
            return new ModelAndView("error/emailError", "user", accountDto);
        }
        return new ModelAndView("success-register", "user", accountDto);
    }

    @GetMapping(value = "/registrationConfirm/{token}")
    public String confirmRegistration(Model model, @PathVariable String token) {

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, null);
            model.addAttribute("message", message);
            return "redirect:/badUser";
        }

        User user = verificationToken.getUser();
//        Calendar cal = Calendar.getInstance();
//        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            String messageValue = messages.getMessage("auth.message.expired", null, locale);
//            model.addAttribute("message", messageValue);
//            return "redirect:/badUser";
//        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return "redirect:/login";
    }
}