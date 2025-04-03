package com.angelldca.siga.infrastructure.adapter.out.persistence.menu;

import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {EventoMapper.class, PlatoMapper.class})
public interface MenuMapper {
    @Mapping(target = "id", source = "id")
    Menu entityToDomain(MenuEntity menuEntity);
    MenuEntity domainToEntity(Menu menu);
}