package com.angelldca.siga.application.port.out.plato;

import com.angelldca.siga.domain.model.Plato;

import java.util.List;

public interface LoadPlatosPort {
    List<Plato> loadAllByIds(List<Long> ids);
}
