package com.angelldca.siga.application.port.in.command;

import com.angelldca.siga.domain.model.Plato;

public interface DeleteUseCase<T,L> {
    T delete(L id);
}
