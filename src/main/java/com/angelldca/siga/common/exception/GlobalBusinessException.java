package com.angelldca.siga.common.exception;

import com.angelldca.siga.domain.rule.DomainErrorMessage;
import lombok.Getter;


@Getter
public class GlobalBusinessException {
    private final DomainErrorMessage error;

    private final ErrorField errorField;

    public GlobalBusinessException(DomainErrorMessage error, ErrorField errorField) {
        this.error = error;
        this.errorField = errorField;
    }
}
