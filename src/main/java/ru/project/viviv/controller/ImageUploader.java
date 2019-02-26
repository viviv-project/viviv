package ru.project.viviv.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.project.viviv.model.entity.Profile;
import ru.project.viviv.model.service.ProfileService;
import ru.project.viviv.model.service.UserService;

import java.security.Principal;

@Controller
public class ImageUploader {

    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;

    private static final Logger log = LogManager.getLogger(ImageUploader.class);

    @PostMapping(value = "upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Principal principal) {
        if (!file.isEmpty()) {
            try {
                Profile profile = userService.findByUsername(principal.getName()).getProfile();
                profile.setAvatarImage(file.getBytes());
                profileService.saveProfile(profile);
                return "redirect:/" + principal.getName();
            } catch (Exception e) {
                log.info(principal.getName() + " не удалось загрузить аватар " + e.getMessage());
                return "profile";
            }
        } else {
            return "profile";
        }
    }
}