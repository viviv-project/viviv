package ru.project.viviv.validation;

public class UsernameExistsException extends ExistsException {

    public UsernameExistsException(final String message, final String fieldError) {
        super(message, fieldError);
    }
}