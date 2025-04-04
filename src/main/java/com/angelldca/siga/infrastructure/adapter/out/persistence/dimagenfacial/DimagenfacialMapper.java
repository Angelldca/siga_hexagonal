package com.angelldca.siga.infrastructure.adapter.out.persistence.dimagenfacial;

import com.angelldca.siga.domain.model.Dimagenfacial;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaMapper;

public class DimagenfacialMapper {
    public static Dimagenfacial entityToDomain(DimagenfacialEntity entity) {
        Dimagenfacial domain = new Dimagenfacial();
        domain.setPersona(DpersonaMapper.entityToDomain(entity.getPersona()));
        domain.setFoto(entity.getFoto());
        domain.setValida(entity.getValida());
        return domain;
    }

    public static DimagenfacialEntity domainToEntity(Dimagenfacial domain) {
        DimagenfacialEntity entity = new DimagenfacialEntity();
        entity.setPersona(DpersonaMapper.domainToEntity(domain.getPersona()));
        entity.setFoto(domain.getFoto());
        entity.setValida(domain.getValida());
        return entity;
    }
}
