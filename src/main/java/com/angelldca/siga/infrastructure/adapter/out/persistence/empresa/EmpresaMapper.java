package com.angelldca.siga.infrastructure.adapter.out.persistence.empresa;

import com.angelldca.siga.domain.model.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


public class EmpresaMapper {

    public static Empresa entityToDomain(EmpresaEntity entity) {
        if (entity == null) return null;

        Empresa domain = new Empresa();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        domain.setLogo(entity.getLogo());
        domain.setAddress(entity.getAddress());
        return domain;
    }

    public static EmpresaEntity domainToEntity(Empresa domain) {
        if (domain == null) return null;

        EmpresaEntity entity = new EmpresaEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setLogo(domain.getLogo());
        entity.setAddress(domain.getAddress());
        return entity;
    }
}