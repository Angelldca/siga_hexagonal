package com.angelldca.siga.infrastructure.adapter.out.persistence.Evento;

import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;

public class EventoMapper {

    public static Evento entityToDomain(EventoEntity entity) {
        if (entity == null) return null;

        Evento evento = new Evento();
        evento.setId(entity.getId());
        evento.setNombre(entity.getNombre());
        evento.setFechaInicio(entity.getFechaInicio());
        evento.setFechaFin(entity.getFechaFin());
        evento.setHoraInicio(entity.getHoraInicio());
        evento.setHoraFin(entity.getHoraFin());
        evento.setActivo(entity.getActivo());
        evento.setIlimitado(entity.getIlimitado());
        evento.setType(entity.getType());
        evento.setEmpresa(EmpresaMapper.entityToDomain(entity.getEmpresa()));
        evento.setIsDelete(entity.getIsDelete());

        return evento;
    }

    public static EventoEntity domainToEntity(Evento domain) {
        if (domain == null) return null;

        EventoEntity entity = new EventoEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setFechaInicio(domain.getFechaInicio());
        entity.setFechaFin(domain.getFechaFin());
        entity.setHoraInicio(domain.getHoraInicio());
        entity.setHoraFin(domain.getHoraFin());
        entity.setIlimitado(domain.getIlimitado());
        entity.setActivo(domain.getActivo());
        entity.setType(domain.getType());
        entity.setEmpresa(EmpresaMapper.domainToEntity(domain.getEmpresa()));
        entity.setIsDelete(domain.getIsDelete());

        return entity;
    }
}