package com.angelldca.siga.application.port.out.module;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.Module;
import com.angelldca.siga.infrastructure.adapter.out.persistence.modulos.ModuleEntity;

public interface ModuleCRUDPort extends DeletePort<Module, Long>, GetPort<Module,Long>,
        ListPort<ModuleEntity>, SavePort<Module> {
}
