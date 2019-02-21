package ru.project.viviv.validation;

import lombok.Getter;

public class ExistsException extends Throwable{
    @Getter
    private final String fieldError;

    public ExistsException(final String message, final String fieldError) {
        super(message);
        this.fieldError = fieldError;
    }
}
