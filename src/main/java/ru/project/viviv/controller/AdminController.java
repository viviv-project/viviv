package ru.project.viviv.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.project.viviv.model.dto.RoleDTO;
import ru.project.viviv.model.dto.UserDTO;
import ru.project.viviv.model.entity.Role;
import ru.project.viviv.model.entity.RoleConnection;
import ru.project.viviv.model.entity.RoleStatus;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.repository.RoleRepository;
import ru.project.viviv.model.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;

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
        model.put("role", new RoleDTO());
        return "user-edit";
    }

    @RequestMapping(value = {"/user-save"}, method = RequestMethod.POST)
    public String personSave(@ModelAttribute("user") User frontUser, @ModelAttribute("role") RoleDTO roleDTO) {
        User user = userService.getUserById(frontUser.getId());
        user.setEmail(frontUser.getEmail());
        user.setEnabled(frontUser.isEnabled());
        user.setUsername(frontUser.getUsername());
        List<RoleConnection> roles = getActualRoles(roleDTO);
        user.setRoleConnections(roles);
        userService.saveRegisteredUser(user);
        return "redirect:/admin/allUsers";
    }

    @RequestMapping(value = {"/user-remove"}, method = RequestMethod.GET)
    public String personRemove(@RequestParam("id") String userId) {
        userService.removeUserById(userId);
        return "redirect:/admin/allUsers";
    }

    private List<RoleConnection> getActualRoles(RoleDTO roleDTO) {
        List<RoleConnection> roles = new ArrayList<>();
        if (roleDTO.isAdmin()){
            roles.add(new RoleConnection(roleRepository.findByStatus(RoleStatus.ADMIN)));
        }
        if (roleDTO.isUser()){
            roles.add(new RoleConnection(roleRepository.findByStatus(RoleStatus.USER)));
        }
        return roles;
    }
}