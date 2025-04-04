package com.angelldca.siga.infrastructure.adapter.out.persistence.menuEvento;

import com.angelldca.siga.domain.model.MenuEvento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuMapper;

public class MenuEventoMapper {
    public static MenuEvento entityToDomain(MenuEventoEntity entity) {
        MenuEvento domain = new MenuEvento();
        domain.setId(entity.getId());
        domain.setMenu(MenuMapper.entityToDomain(entity.getMenu()));
        domain.setEvento(EventoMapper.entityToDomain(entity.getEvento()));
        return domain;
    }

    public static MenuEventoEntity domainToEntity(MenuEvento domain) {
        MenuEventoEntity entity = new MenuEventoEntity();
        entity.setId(domain.getId());
        entity.setMenu(MenuMapper.domainToEntity(domain.getMenu()));
        entity.setEvento(EventoMapper.domainToEntity(domain.getEvento()));
        return entity;
    }
}
