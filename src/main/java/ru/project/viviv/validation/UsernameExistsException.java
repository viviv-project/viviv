package ru.project.viviv.validation;

@SuppressWarnings("serial")
public class UsernameExistsException extends Throwable {

    public UsernameExistsException(final String message) {
        super(message);
    }
}