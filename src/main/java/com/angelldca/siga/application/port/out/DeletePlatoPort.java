package com.angelldca.siga.application.port.out;

import com.angelldca.siga.domain.model.Plato;

public interface DeletePlatoPort {
    Plato delete(Long id);
}
