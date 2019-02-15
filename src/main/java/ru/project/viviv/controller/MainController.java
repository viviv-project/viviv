package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.repository.UserRepository;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = {"/","/index"})
    public ModelAndView showHomePage(Principal principal) {
        if (principal == null) return new ModelAndView("index", "username", null);
        User user = userRepository.findByEmail(principal.getName());
        return new ModelAndView("index", "username", user.getUsername());
    }
}