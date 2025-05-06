package com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona;

import com.angelldca.siga.domain.model.Dpersona;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;

public class DpersonaMapper {
    public static Dpersona entityToDomain(DpersonaEntity entity) {
        Dpersona domain = new Dpersona();
        domain.setId(entity.getId());
        domain.setArea(entity.getArea());
        domain.setRolinstitucional(entity.getRolinstitucional());
        domain.setNombre(entity.getNombre());
        domain.setSolapin(entity.getSolapin());
        domain.setCarnetidentidad(entity.getCarnetidentidad());
        domain.setProvincia(entity.getProvincia());
        domain.setMunicipio(entity.getMunicipio());
        domain.setSexo(entity.getSexo());
        domain.setResidente(entity.getResidente());
        domain.setFechanacimiento(entity.getFechanacimiento());
        domain.setIdexpediente(entity.getIdexpediente());
        domain.setCodigobarra(entity.getCodigobarra());
        domain.setEstado(entity.getEstado());
        domain.setIsDelete(entity.getIsDelete());
        domain.setEmpresa(EmpresaMapper.entityToDomain(entity.getEmpresa()));
        return domain;
    }

    public static DpersonaEntity domainToEntity(Dpersona domain) {
        DpersonaEntity entity = new DpersonaEntity();
        entity.setId(domain.getId());
        entity.setArea(domain.getArea());
        entity.setRolinstitucional(domain.getRolinstitucional());
        entity.setNombre(domain.getNombre());
        entity.setSolapin(domain.getSolapin());
        entity.setCarnetidentidad(domain.getCarnetidentidad());
        entity.setProvincia(domain.getProvincia());
        entity.setMunicipio(domain.getMunicipio());
        entity.setSexo(domain.getSexo());
        entity.setResidente(domain.getResidente());
        entity.setFechanacimiento(domain.getFechanacimiento());
        entity.setIdexpediente(domain.getIdexpediente());
        entity.setCodigobarra(domain.getCodigobarra());
        entity.setEstado(domain.getEstado());
        entity.setIsDelete(domain.getIsDelete());
        entity.setEmpresa(EmpresaMapper.domainToEntity(domain.getEmpresa()));
        return entity;
    }
}
