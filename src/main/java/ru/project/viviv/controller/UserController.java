package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.viviv.model.service.InterestService;
import ru.project.viviv.model.service.ProfileService;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/user")
    public String user() {
        return "user";
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