package com.angelldca.siga.application.port.in.command.plato;

import com.angelldca.siga.domain.model.Plato;

public interface UpdatePlatoUseCase {
    Plato update(UpdatePlatoCommand command, Long id);
}
