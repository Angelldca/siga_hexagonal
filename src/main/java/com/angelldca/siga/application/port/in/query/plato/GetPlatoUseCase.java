package com.angelldca.siga.application.port.in.query.plato;

import com.angelldca.siga.domain.model.Plato;

public interface GetPlatoUseCase {
    Plato obtenerPorId(Long id);
}
