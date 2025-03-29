package com.angelldca.siga.common.exception;

import com.angelldca.siga.domain.rule.DomainErrorMessage;

public class BusinessExceptionFactory {

    public static BusinessNotFoundException objectNotFound(String field,String objectName ){
        return new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.OBJECT_NOT_FOUND, new ErrorField(field, String.format("%s no encontrado.",objectName))));
    }
}
