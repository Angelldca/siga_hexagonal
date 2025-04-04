package com.angelldca.siga.infrastructure.adapter.out.persistence.zona;

import com.angelldca.siga.domain.model.Zona;

public class ZonaMapper {
    public static Zona entityToDomain(ZonaEntity entity) {
        Zona zona = new Zona();
        zona.setId(entity.getId());
        zona.setNombre(entity.getNombre());
        return zona;
    }

    public static ZonaEntity domainToEntity(Zona zona) {
        ZonaEntity entity = new ZonaEntity();
        entity.setId(zona.getId());
        entity.setNombre(zona.getNombre());
        return entity;
    }
}
