package com.angelldca.siga.infrastructure.adapter.out.persistence.menu;

import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoMapper;

import java.util.List;
import java.util.stream.Collectors;


public class MenuMapper {

    public static Menu entityToDomain(MenuEntity entity) {
        if (entity == null) return null;

        Menu menu = new Menu();
        menu.setId(entity.getId());
        menu.setTotalPrecio(entity.getTotalPrecio());
        menu.setDisponible(entity.getDisponible());
        menu.setEvento(EventoMapper.entityToDomain(entity.getEvento()));

        if (entity.getPlatos() != null) {
            List<Plato> platos = entity.getPlatos().stream()
                    .map(PlatoMapper::entityToDomain)
                    .collect(Collectors.toList());
            menu.setPlatos(platos);
        }

        return menu;
    }

    public static MenuEntity domainToEntity(Menu domain) {
        if (domain == null) return null;

        MenuEntity entity = new MenuEntity();
        entity.setId(domain.getId());
        entity.setTotalPrecio(domain.getTotalPrecio());
        entity.setDisponible(domain.getDisponible());
        entity.setEvento(EventoMapper.domainToEntity(domain.getEvento()));

        if (domain.getPlatos() != null) {
            List<PlatoEntity> platos = domain.getPlatos().stream()
                    .map(PlatoMapper::domainToEntity)
                    .collect(Collectors.toList());
            entity.setPlatos(platos);
        }

        return entity;
    }
}