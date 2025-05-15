package com.angelldca.siga.application.port.out.menuEvento;

import com.angelldca.siga.domain.model.MenuEvento;

import java.util.List;

public interface GetMenuEventoByMenuIdPort {
    MenuEvento getMenuEventoByMenuId(Long menuId);
}
