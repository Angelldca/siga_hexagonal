package com.angelldca.siga.infrastructure.adapter.out.persistence.modulos;


import com.angelldca.siga.application.port.out.module.LoadModulePort;
import com.angelldca.siga.application.port.out.module.ModuleCRUDPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Module;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.ModuleWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.ModuleReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Qualifier("modulePersistenceAdapter")
@PersistenceAdapter
public class ModulePersistenceAdapter implements ModuleCRUDPort, LoadModulePort {

    private final ModuleWriteDataJPARepository command;
    private final ModuleReadDataJPARepository query;

    public ModulePersistenceAdapter(ModuleWriteDataJPARepository command, ModuleReadDataJPARepository query) {
        this.command = command;
        this.query = query;

    }

    @Override
    public Module delete(Long id) {
        Module domain = obtenerPorId(id);
        ModuleEntity entity = ModuleMapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public Module obtenerPorId(Long id) {
        Module entity = this.query.findById(id)
                .map(ModuleMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Permisos de Usuario por Empresa"));
        return entity;
    }

    @Override
    public Page<ModuleEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Module save(Module domain) {
        ModuleEntity entity = this.command.save(ModuleMapper.domainToEntity(domain));
        return ModuleMapper.entityToDomain(entity);
    }

    @Override
    public List<Module> loadAllByIds(List<Long> ids) {
        List<ModuleEntity> entities = query.findAllById(ids);
        return entities.stream()
                .map(ModuleMapper::entityToDomain)
                .toList();
    }
}
