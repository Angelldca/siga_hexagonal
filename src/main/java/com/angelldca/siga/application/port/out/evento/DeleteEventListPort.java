package com.angelldca.siga.application.port.out.evento;

import java.util.List;

public interface DeleteEventListPort {

    void deleteListEvent(List<Long> ids);
}
