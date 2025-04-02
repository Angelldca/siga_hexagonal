package com.angelldca.siga.infrastructure.adapter.out.persistence.modulos;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    Module entityToDomain(ModuleEntity entity);

    ModuleEntity domainToEntity(Module domain);
}
