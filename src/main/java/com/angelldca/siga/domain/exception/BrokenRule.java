package com.angelldca.siga.domain.exception;

public class BrokenRule {

    private final ErrorField errorField;
    private final DomainErrorMessage error;

    public BrokenRule(DomainErrorMessage error, ErrorField errorField) {
        this.errorField = errorField;
        this.error = error;
    }
    public DomainErrorMessage getError() {
        return error;
    }

    public ErrorField getErrorField() {
        return errorField;
    }
}
