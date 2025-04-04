package com.angelldca.siga.infrastructure.adapter.out.persistence.puerta_persona;

import com.angelldca.siga.domain.model.PuertaPersona;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta.PuertaMapper;

public class PuertaPersonaMapper {
    public static PuertaPersona entityToDomain(PuertaPersonaEntity entity) {
        PuertaPersona domain = new PuertaPersona();
        domain.setId(entity.getId());
        domain.setPersona(DpersonaMapper.entityToDomain(entity.getPersona()));
        domain.setPuerta(PuertaMapper.entityToDomain(entity.getPuerta()));
        return domain;
    }

    public static PuertaPersonaEntity domainToEntity(PuertaPersona domain) {
        PuertaPersonaEntity entity = new PuertaPersonaEntity();
        entity.setId(domain.getId());
        entity.setPersona(DpersonaMapper.domainToEntity(domain.getPersona()));
        entity.setPuerta(PuertaMapper.domainToEntity(domain.getPuerta()));
        return entity;
    }
}
