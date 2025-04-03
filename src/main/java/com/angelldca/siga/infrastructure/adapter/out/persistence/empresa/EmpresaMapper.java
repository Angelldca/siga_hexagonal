package com.angelldca.siga.infrastructure.adapter.out.persistence.empresa;

import com.angelldca.siga.domain.model.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    @Mapping(target = "id", source = "id")
    Empresa entityToDomain(EmpresaEntity entity);

    EmpresaEntity domainToEntity(Empresa domain);
}
