package com.angelldca.siga.infrastructure.adapter.out.persistence.zonaEvento;

import com.angelldca.siga.domain.model.ZonaEvento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zona.ZonaMapper;

public class ZonaEventoMapper {
    public static ZonaEvento entityToDomain(ZonaEventoEntity entity) {
        ZonaEvento domain = new ZonaEvento();
        domain.setId(entity.getId());
        domain.setZona(ZonaMapper.entityToDomain(entity.getZona()));
        domain.setEvento(EventoMapper.entityToDomain(entity.getEvento()));
        return domain;
    }

    public static ZonaEventoEntity domainToEntity(ZonaEvento domain) {
        ZonaEventoEntity entity = new ZonaEventoEntity();
        entity.setId(domain.getId());
        entity.setZona(ZonaMapper.domainToEntity(domain.getZona()));
        entity.setEvento(EventoMapper.domainToEntity(domain.getEvento()));
        return entity;
    }
}
