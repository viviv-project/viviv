package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ModelAndView user(@PathVariable(name = "username") String username, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        if (user.getUsername().equals(username))
            return new ModelAndView("profile", "user", user);
        return new ModelAndView("/");
    }

    @RequestMapping("/profile")
    public String profile() {
        return "profile";
    }

    @RequestMapping("/friends")
    public String friends() {
        return "friends";
    }
}