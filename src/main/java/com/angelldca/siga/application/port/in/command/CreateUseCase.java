package com.angelldca.siga.application.port.in.command;

import com.angelldca.siga.domain.model.Plato;

public interface CreateUseCase<T,C> {
    T create(C command);
}
