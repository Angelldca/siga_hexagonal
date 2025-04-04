package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.MenuEvento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class MenuEventoResponse implements IResponse{
    private UUID id;
    private Menu menu;
    private Evento evento;

    public MenuEventoResponse(MenuEvento menuEvento) {
        this.id = menuEvento.getId();
        this.menu = menuEvento.getMenu();
        this.evento = menuEvento.getEvento();
    }
}
