package com.angelldca.siga.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ListPort<T> {
    Page<T> list(Specification specifications, Pageable pageable);
}
