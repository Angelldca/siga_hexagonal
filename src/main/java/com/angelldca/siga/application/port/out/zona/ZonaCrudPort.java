package com.angelldca.siga.application.port.out.zona;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.Zona;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zona.ZonaEntity;

public interface ZonaCrudPort extends DeletePort<Zona, Long>, GetPort<Zona,Long>,
        ListPort<ZonaEntity>, SavePort<Zona> {
}
