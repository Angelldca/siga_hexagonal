package com.angelldca.siga.infrastructure.adapter.out.persistence.plato;

import com.angelldca.siga.domain.model.Plato;

public class PlatoMapper {
    public static Plato entityToDomain(PlatoEntity platoEntity) {
        Plato plato = new Plato();
        plato.setId(platoEntity.getId());
        plato.setDisponible(platoEntity.getDisponible());
        plato.setNombre(platoEntity.getNombre());
        plato.setPrecio(platoEntity.getPrecio());
        plato.setMedida(platoEntity.getMedida());
        return plato;
    }

    public static PlatoEntity domainToEntity(Plato plato) {
        PlatoEntity platoEntity = new PlatoEntity();
        platoEntity.setId(plato.getId());
        platoEntity.setDisponible(plato.getDisponible());
        platoEntity.setPrecio(plato.getPrecio());
        platoEntity.setMedida(plato.getMedida());
        platoEntity.setNombre(plato.getNombre());
        return platoEntity;
    }
}
