package com.angelldca.siga.application.port.in.command.zonaEvento;

import com.angelldca.siga.domain.model.ZonaEvento;

import java.util.List;

public interface BulkCreateZonaEventoUseCase {
    List<ZonaEvento> create(CreateZonaEventoCommand command);
}
