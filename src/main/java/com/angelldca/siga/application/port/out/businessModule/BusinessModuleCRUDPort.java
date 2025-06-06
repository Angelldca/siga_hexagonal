package com.angelldca.siga.application.port.out.businessModule;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.BusinessModule;
import com.angelldca.siga.infrastructure.adapter.out.persistence.business_module.BusinessModuleEntity;

import java.util.UUID;

public interface BusinessModuleCRUDPort extends DeletePort<BusinessModule, UUID>, GetPort<BusinessModule,UUID>,
        ListPort<BusinessModuleEntity>, SavePort<BusinessModule> {
        }
