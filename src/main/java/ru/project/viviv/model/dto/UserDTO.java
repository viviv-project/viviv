package ru.project.viviv.model.dto;

import lombok.Data;
import ru.project.viviv.validation.PasswordMatches;
import ru.project.viviv.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class UserDTO {

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
    private String username;
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
}