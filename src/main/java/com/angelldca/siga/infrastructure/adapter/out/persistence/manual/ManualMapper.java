package com.angelldca.siga.infrastructure.adapter.out.persistence.manual;

import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Manual;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;

public class ManualMapper {
    public static Manual entityToDomain(ManualEntity entity) {
        if (entity == null) return null;

        Manual domain = new Manual();
        domain.setDescripcion(entity.getDescripcion());
        domain.setNombre(entity.getNombre());
        domain.setFilePath(entity.getFilePath());
        domain.setId(entity.getId());
        domain.setEmpresa(EmpresaMapper.entityToDomain(entity.getEmpresa()));


        return domain;
    }

    public static ManualEntity domainToEntity(Manual domain) {
        if (domain == null) return null;
        ManualEntity entity = new ManualEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());
        entity.setFilePath(domain.getFilePath());
        entity.setEmpresa(EmpresaMapper.domainToEntity(domain.getEmpresa()));
        return entity;
    }
}
