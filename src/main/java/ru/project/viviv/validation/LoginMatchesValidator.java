package ru.project.viviv.validation;

import ru.project.viviv.model.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class LoginMatchesValidator implements ConstraintValidator<LoginMatches, Object> {

    @Override
    public void initialize(final LoginMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserDTO user = (UserDTO) obj;
        return user.getUsername().equals(user.getMatchingUsername());
    }
}