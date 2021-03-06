package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.project.viviv.model.repository.UserRepository;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = {"/", "/index"})
    public ModelAndView showHomePage(Principal principal) {
        if (principal == null) return new ModelAndView("index", "username", null);
        return new ModelAndView("index", "username", principal.getName());
    }
}