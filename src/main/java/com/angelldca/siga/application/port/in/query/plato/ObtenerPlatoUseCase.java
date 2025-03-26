package com.angelldca.siga.application.port.in.query.plato;

import com.angelldca.siga.domain.model.Plato;

public interface ObtenerPlatoUseCase {
    Plato obtenerPorId(Long id);
}
