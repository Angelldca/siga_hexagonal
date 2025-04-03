package com.angelldca.siga.application.port.out.business;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;


import java.util.UUID;

public interface BusinessCRUDPort extends DeletePort<Empresa, UUID>, GetPort<Empresa,UUID>,
        ListPort<EmpresaEntity>, SavePort<Empresa> {
}
