package ru.project.viviv.model.dto;

import lombok.Data;

@Data
public class ProfileEditDTO {
    private String firstname;
    private String lastname;
    private String age;
    private Character cSex;
    private String phone;
}
