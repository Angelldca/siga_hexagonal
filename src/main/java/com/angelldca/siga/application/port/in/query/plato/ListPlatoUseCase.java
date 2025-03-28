package com.angelldca.siga.application.port.in.query.plato;

import com.angelldca.siga.domain.utils.response.PaginatedResponse;
import com.angelldca.siga.domain.utils.response.FilterCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListPlatoUseCase {
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
