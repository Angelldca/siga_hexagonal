package com.angelldca.siga.application.port.out.zonaEvento;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.ZonaEvento;

import com.angelldca.siga.infrastructure.adapter.out.persistence.zonaEvento.ZonaEventoEntity;

import java.util.UUID;

public interface ZonaEventoCrudPort extends DeletePort<ZonaEvento, UUID>,
        GetPort<ZonaEvento,UUID>,
        ListPort<ZonaEventoEntity>, SavePort<ZonaEvento> {
}
