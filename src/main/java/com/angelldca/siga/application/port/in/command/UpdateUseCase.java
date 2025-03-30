package com.angelldca.siga.application.port.in.command;

import com.angelldca.siga.domain.model.Plato;

public interface UpdateUseCase<T,C,L> {
    T update(C command, L id);
}
