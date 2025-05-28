package com.angelldca.siga.application.port.out.manual;

import com.angelldca.siga.infrastructure.adapter.out.persistence.manual.ManualEntity;

import java.util.List;

public interface GetByNombrePort {
    List<ManualEntity> buscarPorNombreAproximado(String nombreParcial);
}
