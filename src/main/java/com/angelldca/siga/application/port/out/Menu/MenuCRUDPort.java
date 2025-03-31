package com.angelldca.siga.application.port.out.Menu;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;



public interface MenuCRUDPort extends DeletePort<Menu>, GetPort<Menu>,
        ListPort<MenuEntity>, SavePort<Menu> {
}
