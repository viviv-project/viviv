package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.service.FriendService;
import ru.project.viviv.model.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

    @GetMapping("{username}")
    public ModelAndView user(@PathVariable(name = "username") String username, Principal principal) {
        if (isForbidden(username, principal)) {
            return new ModelAndView("/");
        }
        return new ModelAndView("profile", "username", username);
    }

    @GetMapping("friends")
    public ModelAndView friends(Principal principal) {
        List<User> friends = friendService.findAllUserFriends(userService.findByUsername(principal.getName()));
        return new ModelAndView("friends", "friends", friends);
    }

    @GetMapping("allUsers")
    public ModelAndView allUsers(Principal principal) {
        List<User> users = userService.getAllUsers();
        users.remove(userService.findByUsername(principal.getName()));
        users.removeAll(friendService.findAllUserRelations(userService.findByUsername(principal.getName())));
        return new ModelAndView("all-users", "users", users);
    }

    private boolean isForbidden(String username, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return !user.getUsername().equals(username);
    }

    //todo метод для отладки, удалить позже
    @PostMapping("addFriend")
    public RedirectView addFriend(@RequestParam String friendUsername, Principal principal){
        userService.addFriend(principal.getName(), friendUsername);
        return new RedirectView("allUsers");
    }
}