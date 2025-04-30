package com.angelldca.siga.application.port.out.evento;

import com.angelldca.siga.domain.model.Evento;

import java.util.List;

public interface GetAllEventById {
    List<Evento> getAllById(List<Long> ids);
}
