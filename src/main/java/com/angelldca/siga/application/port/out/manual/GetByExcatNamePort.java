package com.angelldca.siga.application.port.out.manual;

import com.angelldca.siga.infrastructure.adapter.out.persistence.manual.ManualEntity;

import java.util.Optional;

public interface GetByExcatNamePort {
    Optional<ManualEntity> obtenerPorNombreExacto(String nombre);
}
