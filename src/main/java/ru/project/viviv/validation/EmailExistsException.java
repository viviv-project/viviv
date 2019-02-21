package ru.project.viviv.validation;

public class EmailExistsException extends ExistsException {

    public EmailExistsException(final String message, final String fieldError) {
        super(message, fieldError);
    }
}