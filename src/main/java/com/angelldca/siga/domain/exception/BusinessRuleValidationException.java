package com.angelldca.siga.domain.exception;


import lombok.Getter;

@Getter
public class BusinessRuleValidationException extends RuntimeException{
    private final int status;
    private final transient BrokenRule brokenRule;
    private final String message;
    private final String details;


    public BusinessRuleValidationException(BrokenRule brokenRule) {
        super(brokenRule.getError().getMessage());
        this.status = brokenRule.getError().getCode();
        this.brokenRule = brokenRule;
        this.message = brokenRule.getError().getMessage();
        this.details = brokenRule.getErrorField().getMessage();
    }
}
