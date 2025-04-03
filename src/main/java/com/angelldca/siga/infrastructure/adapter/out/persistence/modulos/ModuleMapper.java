package com.angelldca.siga.infrastructure.adapter.out.persistence.modulos;


import com.angelldca.siga.domain.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    @Mapping(target = "id", source = "id")
    Module entityToDomain(ModuleEntity entity);

    ModuleEntity domainToEntity(Module domain);
}
