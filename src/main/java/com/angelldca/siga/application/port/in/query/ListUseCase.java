package com.angelldca.siga.application.port.in.query;

import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.common.criteria.FilterCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListUseCase {
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
}
