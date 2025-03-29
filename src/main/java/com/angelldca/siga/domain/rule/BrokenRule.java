package com.angelldca.siga.domain.rule;

import com.angelldca.siga.common.exception.ErrorField;


public abstract class BrokenRule {

    private final ErrorField errorField;
    private final DomainErrorMessage error;

    public BrokenRule(DomainErrorMessage error, ErrorField errorField) {
        this.errorField = errorField;
        this.error = error;
    }

    public abstract boolean isBroken();
    public DomainErrorMessage getError() {
        return error;
    }

    public ErrorField getErrorField() {
        return errorField;
    }
}
