package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.viviv.model.service.InterestService;
import ru.project.viviv.model.service.ProfileService;

@RestController
public class MainController {
    @Autowired
    ProfileService profileService;
    @Autowired
    InterestService interestService;

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }
}