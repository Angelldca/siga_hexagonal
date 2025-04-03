package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.BusinessModule;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.domain.model.Module;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BusinessModuleResponse implements IResponse{
    private UUID id;
    private Empresa business;
    private Module module;

    public BusinessModuleResponse(BusinessModule businessModule) {
        this.id = businessModule.getId();
        this.business = businessModule.getBusiness();
        this.module = businessModule.getModule();
    }
}
