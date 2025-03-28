package com.angelldca.siga.application.port.in.query.plato;

import com.angelldca.siga.domain.model.Plato;

import java.util.List;

public interface ListPlatoUseCase {
    List<Plato> search(Long id);
}
