package com.angelldca.siga.infrastructure.adapter.out.persistence.modulos;


import com.angelldca.siga.domain.model.Module;


public class ModuleMapper {

    public static Module entityToDomain(ModuleEntity entity) {
        if (entity == null) return null;
        Module module = new Module();
        module.setId(entity.getId());
        module.setName(entity.getName());
        module.setImage(entity.getImage());
        module.setDescription(entity.getDescription());
        return module;
    }

    public static ModuleEntity domainToEntity(Module domain) {
        if (domain == null) return null;
        ModuleEntity entity = new ModuleEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setImage(domain.getImage());
        entity.setDescription(domain.getDescription());
        return entity;
    }
}
