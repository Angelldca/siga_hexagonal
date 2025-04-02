package com.angelldca.siga.application.port.out;

import com.angelldca.siga.domain.model.Plato;

import java.util.Optional;

public interface GetPort<T,I> {
    T obtenerPorId(I id);
}
