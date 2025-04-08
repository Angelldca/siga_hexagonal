package com.angelldca.siga.application.port.out.menuEvento;

import com.angelldca.siga.domain.model.MenuEvento;


import java.util.Optional;
import java.util.UUID;

public interface GetOptionalMenuEventoPort {
    MenuEvento loadMenuEvento(UUID id);
}
