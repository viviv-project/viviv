package ru.project.viviv.model.dto;

import lombok.Data;

@Data
public class RegistrationErrorDTO {
    private String emailError;
    private String usernameError;
}
