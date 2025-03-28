package com.angelldca.siga.application.port.out;

import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ListPlatosPort {
    Page<PlatoEntity> list(Specification specifications, Pageable pageable);
}
