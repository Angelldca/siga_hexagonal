package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Zona;
import com.angelldca.siga.domain.model.ZonaEvento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ZonaEventoResponse implements IResponse{
    private UUID id;
    private ZonaResponse zona;
    private EventoResponse evento;

    public ZonaEventoResponse(ZonaEvento zonaEvento) {
        this.id = zonaEvento.getId();
        this.zona = new ZonaResponse(zonaEvento.getZona());
        this.evento = new EventoResponse(zonaEvento.getEvento());
    }
}
