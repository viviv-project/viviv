package ru.project.viviv.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;

@Controller
public class ImageUploader {

    private static String UPLOADED_FOLDER = "images/";
    private static final Logger log = LogManager.getLogger(ImageUploader.class);

    @PostMapping(value = "upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Principal principal) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(UPLOADED_FOLDER + principal.getName());
                dir.mkdirs();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(dir + "/" + file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                return "index";
            } catch (Exception e) {
                log.info(principal.getName() + " не удалось загрузить аватар " + e.getMessage());
                return "profile";
            }
        } else {
            return "profile";
        }
    }
}