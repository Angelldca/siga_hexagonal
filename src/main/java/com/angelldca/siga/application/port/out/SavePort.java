package com.angelldca.siga.application.port.out;

import com.angelldca.siga.domain.model.Plato;

public interface SavePort<T> {
    T save(Plato plato);
}
