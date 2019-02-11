package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String user() {
        return "admin";
    }

    @RequestMapping("/users")
    public String profile() {
        return "users";
    }

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        model.addAttribute("users",userService.getAllUsers());
        return "all-users";
    }

    @RequestMapping(value = {"/user-edit"}, method = RequestMethod.GET)
    public String personEdit(@RequestParam("id") String userId, Map<String,Object> model){
        final User user =  userService.getUserById(userId);
        model.put("user", user);
        return "user-edit";
    }

    @RequestMapping(value = {"/user-save"}, method = RequestMethod.POST)
    public String personSave(@ModelAttribute("user") User user) {
        userService.saveRegisteredUser(user);
        return "redirect:/allUsers";
    }

    @RequestMapping(value = {"/user-remove"}, method = RequestMethod.GET)
    public String personRemove(@RequestParam("id") String userId) {
        userService.removeUserById(userId);
        return "redirect:/allUsers";
    }

}