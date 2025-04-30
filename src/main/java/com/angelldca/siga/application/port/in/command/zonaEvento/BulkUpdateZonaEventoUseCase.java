package com.angelldca.siga.application.port.in.command.zonaEvento;

import com.angelldca.siga.domain.model.ZonaEvento;

import java.util.List;
import java.util.UUID;

public interface BulkUpdateZonaEventoUseCase {
    List<ZonaEvento> update(UpdateZonaEventoCommand command);
}
