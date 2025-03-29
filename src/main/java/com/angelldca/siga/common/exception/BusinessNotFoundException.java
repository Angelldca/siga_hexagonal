package com.angelldca.siga.common.exception;

import lombok.Getter;

public class BusinessNotFoundException extends RuntimeException{
    @Getter
    private final GlobalBusinessException brokenRule;

    @Getter
    private final int status;

    private final String message;

    public BusinessNotFoundException(GlobalBusinessException brokenRule) {
        super(brokenRule.getError().getMessage());
        this.status = brokenRule.getError().getCode();
        this.message = brokenRule.getError().getMessage();
        this.brokenRule = brokenRule;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
