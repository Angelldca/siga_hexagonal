package com.angelldca.siga.application.port.out.evento;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoEntity;

public interface EventoPort extends DeletePort<Evento,Long>, GetPort<Evento,Long>,
        ListPort<EventoEntity>, SavePort<Evento> {
}
