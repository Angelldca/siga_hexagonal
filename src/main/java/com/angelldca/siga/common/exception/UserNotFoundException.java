package com.angelldca.siga.common.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final ErrorField errorField;

    public UserNotFoundException(String message, ErrorField errorField) {
        super(message);
        this.errorField = errorField;
    }

}