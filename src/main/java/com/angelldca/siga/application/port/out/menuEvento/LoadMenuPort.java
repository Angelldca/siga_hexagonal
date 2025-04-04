package com.angelldca.siga.application.port.out.menuEvento;

import com.angelldca.siga.domain.model.Module;

import java.util.List;

public interface LoadMenuPort {
    List<Module> loadAllByIds(List<Long> ids);
}
