package com.angelldca.siga.application.port.out.zonaEvento;

import com.angelldca.siga.domain.model.ZonaEvento;

import java.util.List;

public interface SaveAllZonaEvento {

    List<ZonaEvento> saveAllZonaEvento(List<ZonaEvento> zonaEventos);
}
