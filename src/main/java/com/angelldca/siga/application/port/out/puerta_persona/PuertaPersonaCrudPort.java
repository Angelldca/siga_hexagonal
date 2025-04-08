package com.angelldca.siga.application.port.out.puerta_persona;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.PuertaPersona;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta_persona.PuertaPersonaEntity;

import java.util.UUID;

public interface PuertaPersonaCrudPort extends DeletePort<PuertaPersona, UUID>, GetPort<PuertaPersona,UUID>,
        ListPort<PuertaPersonaEntity>, SavePort<PuertaPersona> {
}
