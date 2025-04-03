package com.angelldca.siga.application.port.out.module;

import com.angelldca.siga.domain.model.Module;

import java.util.List;

public interface LoadModulePort {
    List<Module> loadAllByIds(List<Long> ids);
}
