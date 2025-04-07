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
    private Zona zona;
    private Evento evento;

    public ZonaEventoResponse(ZonaEvento zonaEvento) {
        this.id = zonaEvento.getId();
        this.zona = zonaEvento.getZona();
        this.evento = zonaEvento.getEvento();
    }
}
