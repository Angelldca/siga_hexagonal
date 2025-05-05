package com.angelldca.siga.infrastructure.adapter.out.persistence.puerta;

import com.angelldca.siga.domain.model.Puerta;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zona.ZonaMapper;

public class PuertaMapper {
    public static Puerta entityToDomain(PuertaEntity entity) {
        Puerta domain = new Puerta();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        domain.setZona(ZonaMapper.entityToDomain(entity.getZona()));
        domain.setIsDelete(entity.getIsDelete());
        return domain;
    }

    public static PuertaEntity domainToEntity(Puerta domain) {
        PuertaEntity entity = new PuertaEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setZona(ZonaMapper.domainToEntity(domain.getZona()));
        entity.setIsDelete(domain.getIsDelete());
        return entity;
    }
}
