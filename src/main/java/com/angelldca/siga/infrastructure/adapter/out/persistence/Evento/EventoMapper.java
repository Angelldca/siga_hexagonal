package com.angelldca.siga.infrastructure.adapter.out.persistence.Evento;

import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoMapper;

public class EventoMapper {
    public static Evento entityToDomain(EventoEntity entity) {
        Evento evento = new Evento();
        evento.setId(entity.getId());
        evento.setActivo(entity.getActivo());
        evento.setFechaInicio(entity.getFechaInicio());
        evento.setFechaFin(entity.getFechaFin());
        evento.setNombre(entity.getNombre());
        return evento;
    }

    public static EventoEntity domainToEntity(Evento evento) {
        EventoEntity entity = new EventoEntity();
        entity.setId(evento.getId());
        entity.setActivo(evento.getActivo());
        entity.setFechaInicio(evento.getFechaInicio());
        entity.setFechaFin(evento.getFechaFin());
        entity.setNombre(evento.getNombre());
        return entity;
    }
}
