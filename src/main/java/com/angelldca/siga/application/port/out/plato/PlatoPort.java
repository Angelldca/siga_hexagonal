package com.angelldca.siga.application.port.out.plato;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoEntity;

public interface PlatoPort extends DeletePort<Plato,Long>, GetPort<Plato,Long>,
        ListPort<PlatoEntity>, SavePort<Plato> {
}
