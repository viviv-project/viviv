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
    private String birthDate;
    private int sex;
    private String phone;
    private int age;
    private Character charSex;
}
