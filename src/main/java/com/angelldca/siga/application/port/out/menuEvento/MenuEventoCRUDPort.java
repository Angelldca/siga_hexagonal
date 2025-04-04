package com.angelldca.siga.application.port.out.menuEvento;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.MenuEvento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menuEvento.MenuEventoEntity;

import java.util.UUID;

public interface MenuEventoCRUDPort extends DeletePort<MenuEvento, UUID>, GetPort<MenuEvento,UUID>,
        ListPort<MenuEventoEntity>, SavePort<MenuEvento> {
}
