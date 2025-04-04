package com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona;

import com.angelldca.siga.domain.model.Dpersona;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;

public class DpersonaMapper {
    public static Dpersona entityToDomain(DpersonaEntity entity) {
        Dpersona domain = new Dpersona();
        domain.setIdciudadano(entity.getIdciudadano());
        domain.setArea(entity.getArea());
        domain.setRolinstitucional(entity.getRolinstitucional());
        domain.setPrimernombre(entity.getPrimernombre());
        domain.setSegundonombre(entity.getSegundonombre());
        domain.setPrimerapellido(entity.getPrimerapellido());
        domain.setSegundoapellido(entity.getSegundoapellido());
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
        domain.setEmpresa(EmpresaMapper.entityToDomain(entity.getEmpresa())); // asegúrate de tener EmpresaMapper
        return domain;
    }

    public static DpersonaEntity domainToEntity(Dpersona domain) {
        DpersonaEntity entity = new DpersonaEntity();
        entity.setIdciudadano(domain.getIdciudadano());
        entity.setArea(domain.getArea());
        entity.setRolinstitucional(domain.getRolinstitucional());
        entity.setPrimernombre(domain.getPrimernombre());
        entity.setSegundonombre(domain.getSegundonombre());
        entity.setPrimerapellido(domain.getPrimerapellido());
        entity.setSegundoapellido(domain.getSegundoapellido());
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
        entity.setEmpresa(EmpresaMapper.domainToEntity(domain.getEmpresa())); // asegúrate de tener EmpresaMapper
        return entity;
    }
}
