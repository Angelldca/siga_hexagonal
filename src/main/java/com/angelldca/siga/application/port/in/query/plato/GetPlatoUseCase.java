package com.angelldca.siga.application.port.in.query.plato;

import com.angelldca.siga.common.response.IResponse;

public interface GetPlatoUseCase {
    IResponse getPlatoById(Long id);
}
