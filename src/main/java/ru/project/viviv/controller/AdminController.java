package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.project.viviv.model.dto.RoleDTO;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.service.FriendService;
import ru.project.viviv.model.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

    @RequestMapping("/")
    public String user() {
        return "admin";
    }

    @RequestMapping("/users")
    public String profile() {
        return "users";
    }

    //todo заменить persistent entity на DTO
    @GetMapping(value = "/allUsers")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-all-users";
    }

    @GetMapping(value = {"/user-edit"})
    public String personEdit(@RequestParam("id") String userId, Map<String, Object> model) {
        final User user = userService.getUserById(userId);
        model.put("user", user);
        model.put("role", new RoleDTO());
        return "user-edit";
    }

    @PostMapping(value = {"/user-save"})
    public String personSave(@ModelAttribute("user") User frontUser, @ModelAttribute("role") RoleDTO roleDTO) {
        User user = userService.getUserById(frontUser.getId());
        user.setEmail(frontUser.getEmail());
        user.setEnabled(frontUser.getEnabled());
        user.setUsername(frontUser.getUsername());
        user = userService.updateRoles(roleDTO, user);
        userService.saveUser(user);
        return "redirect:allUsers";
    }

    @GetMapping(value = {"/user-remove"})
    public String personRemove(@RequestParam("id") String userId) {
        User user = userService.getUserById(userId);
        friendService.removeAllFriends(user);
        userService.removeUserById(userId);
        return "redirect:allUsers";
    }
}