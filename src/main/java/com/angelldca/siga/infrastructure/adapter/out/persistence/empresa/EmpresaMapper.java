package com.angelldca.siga.infrastructure.adapter.out.persistence.empresa;

import com.angelldca.siga.domain.model.Empresa;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    Empresa entityToDomain(EmpresaEntity entity);

    EmpresaEntity domainToEntity(Empresa domain);
}
