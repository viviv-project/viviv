package ru.project.viviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;

@Controller
public class ImageUploader {

    @Autowired
    private HttpServletRequest request;

    private static String UPLOADED_FOLDER = "/images";

    @PostMapping(value = "/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Principal principal) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                new File("images/" + principal.getName()).mkdirs();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(UPLOADED_FOLDER + principal.getName() + "/" + file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                return "index";
            } catch (Exception e) {
                return "Вам не удалось загрузить => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить потому что файл пустой.";
        }
    }
}