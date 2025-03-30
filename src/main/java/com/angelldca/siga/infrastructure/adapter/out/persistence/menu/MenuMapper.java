package com.angelldca.siga.infrastructure.adapter.out.persistence.menu;

import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoMapper;


public class MenuMapper {
    public static Menu entityToDomain(MenuEntity menuEntity) {
     Menu menu = new Menu();
     menu.setId(menuEntity.getId());
     menu.setDisponible(menuEntity.getDisponible());
     menu.setTotalPrecio(menuEntity.getTotalPrecio());
     menu.setEvento(EventoMapper.entityToDomain(menuEntity.getEvento()));
     menu.setPlatos(menuEntity.getPlatos().stream().map(
             PlatoMapper::entityToDomain
     ).toList());
        return menu;
    }

    public static MenuEntity domainToEntity(Menu menu) {
        MenuEntity entity = new MenuEntity();
        entity.setId(menu.getId());
        entity.setDisponible(menu.getDisponible());
        entity.setTotalPrecio(menu.getTotalPrecio());
        entity.setEvento(EventoMapper.domainToEntity(menu.getEvento()));
        entity.setPlatos(menu.getPlatos().stream().map(
                PlatoMapper::domainToEntity
        ).toList());
        return entity;
    }
}
