package com.angelldca.siga.application.port.out.puerta;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.Puerta;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta.PuertaEntity;

public interface PuertaCrudPort extends DeletePort<Puerta,Long>, GetPort<Puerta,Long>,
        ListPort<PuertaEntity>, SavePort<Puerta> {
}
