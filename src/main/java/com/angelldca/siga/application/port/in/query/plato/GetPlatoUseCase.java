package com.angelldca.siga.application.port.in.query.plato;

import com.angelldca.siga.application.port.in.query.response.IResponse;
import com.angelldca.siga.application.port.in.query.response.PlatoResponse;
import com.angelldca.siga.domain.model.Plato;

public interface GetPlatoUseCase {
    IResponse getPlatoById(Long id);
}
