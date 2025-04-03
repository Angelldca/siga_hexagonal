package com.angelldca.siga.infrastructure.adapter.out.persistence.Evento;

import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EmpresaMapper.class})
public interface EventoMapper {
    @Mapping(target = "id", source = "id")
    EventoEntity domainToEntity(Evento domain);

    Evento entityToDomain(EventoEntity entity);
}