package com.angelldca.siga.infrastructure.adapter.out.persistence.zona;

import com.angelldca.siga.domain.model.Zona;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;

public class ZonaMapper {
    public static Zona entityToDomain(ZonaEntity entity) {
        Zona zona = new Zona();
        zona.setId(entity.getId());
        zona.setNombre(entity.getNombre());
        zona.setEmpresa(EmpresaMapper.entityToDomain(entity.getEmpresa()));
        zona.setIsDelete(entity.getIsDelete());
        return zona;
    }

    public static ZonaEntity domainToEntity(Zona zona) {
        ZonaEntity entity = new ZonaEntity();
        entity.setId(zona.getId());
        entity.setNombre(zona.getNombre());
        entity.setEmpresa(EmpresaMapper.domainToEntity(zona.getEmpresa()));
        entity.setIsDelete(zona.getIsDelete());
        return entity;
    }
}
