package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.viviv.model.service.InterestService;
import ru.project.viviv.model.service.ProfileService;

@Controller
public class MainController {

    @RequestMapping(value = {"/","/index"})
    public String showHomePage() {
        return "index";
    }


}