package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.project.viviv.common.ConverterDTO;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ModelAndView user(@PathVariable(name = "username") String username, Principal principal) {
        if (isForbidden(username, principal)) {
            return new ModelAndView("/");
        }
        return new ModelAndView("profile", "username", username);
    }

    @GetMapping("{username}/friends")
    public ModelAndView friends(@PathVariable(name = "username") String username, Principal principal) {
        if (isForbidden(username, principal)) {
            return new ModelAndView("/");
        }
        return new ModelAndView("friends", "username", username);
    }

    private boolean isForbidden(String username, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return !user.getUsername().equals(username);
    }
}