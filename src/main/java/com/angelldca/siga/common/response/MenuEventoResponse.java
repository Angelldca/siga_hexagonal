package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.MenuEvento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class MenuEventoResponse implements IResponse{
    private UUID id;
    private MenuResponse menu;
    private EventoResponse evento;
    private LocalDate fecha;

    public MenuEventoResponse(MenuEvento menuEvento) {
        this.id = menuEvento.getId();
        this.menu = new MenuResponse(menuEvento.getMenu());
        this.evento = new EventoResponse(menuEvento.getEvento());
        this.fecha = menuEvento.getFecha();
    }
}
