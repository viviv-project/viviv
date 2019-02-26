package ru.project.viviv.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileDTO {
    private LocalDateTime dateAdded;
    private String firstname;
    private String lastname;
    private String avatarImage;
    private String middlename;
}
