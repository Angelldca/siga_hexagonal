package com.angelldca.siga.application.port.in.query;

import com.angelldca.siga.common.response.IResponse;

public interface GetUseCase<L> {
    IResponse getPlatoById(L id);
}
