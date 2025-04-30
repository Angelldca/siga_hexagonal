package com.angelldca.siga.application.port.out.zonaEvento;

import com.angelldca.siga.domain.model.Evento;

import java.util.List;

public interface GetEventByZonaId {
    List<Evento>getEventByZonaId(Long zonaId);
}
