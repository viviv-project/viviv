package ru.project.viviv.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.viviv.validation.PasswordMatches;
import ru.project.viviv.validation.ValidEmail;
import ru.project.viviv.validation.ValidLogin;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
    @ValidLogin
    @NotNull
    @NotEmpty
    private String username;
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    public UserDTO(@NotNull @NotEmpty String password, @NotNull @NotEmpty String username, @NotNull @NotEmpty String email) {
        this.password = password;
        this.username = username;
        this.email = email;
    }
}