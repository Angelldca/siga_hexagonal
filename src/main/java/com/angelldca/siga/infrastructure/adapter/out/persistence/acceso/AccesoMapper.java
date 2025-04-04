package com.angelldca.siga.infrastructure.adapter.out.persistence.acceso;

import com.angelldca.siga.domain.model.Acceso;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menuEvento.MenuEventoMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta_persona.PuertaPersonaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zonaEvento.ZonaEventoMapper;

public class AccesoMapper {
    public static Acceso entityToDomain(AccesoEntity entity) {
        Acceso domain = new Acceso();
        domain.setId(entity.getId());
        domain.setPuertaPersona(PuertaPersonaMapper.entityToDomain(entity.getPuertaPersona()));
        domain.setZonaEvento(ZonaEventoMapper.entityToDomain(entity.getZonaEvento()));
        domain.setMenuEvento(entity.getMenuEvento() != null ? MenuEventoMapper.entityToDomain(entity.getMenuEvento()) : null);
        return domain;
    }

    public static AccesoEntity domainToEntity(Acceso domain) {
        AccesoEntity entity = new AccesoEntity();
        entity.setId(domain.getId());
        entity.setPuertaPersona(PuertaPersonaMapper.domainToEntity(domain.getPuertaPersona()));
        entity.setZonaEvento(ZonaEventoMapper.domainToEntity(domain.getZonaEvento()));
        entity.setMenuEvento(domain.getMenuEvento() != null ? MenuEventoMapper.domainToEntity(domain.getMenuEvento()) : null);
        return entity;
    }
}
