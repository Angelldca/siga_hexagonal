package com.angelldca.siga.application.port.in.query;

import com.angelldca.siga.common.criteria.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
public class SearchQuery {
    private Pageable pageable;
    private List<FilterCriteria> filter;
    private String query;
}
