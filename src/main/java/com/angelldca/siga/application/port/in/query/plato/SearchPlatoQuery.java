package com.angelldca.siga.application.port.in.query.plato;

import com.angelldca.siga.domain.utils.response.FilterCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
public class SearchPlatoQuery {
    private Pageable pageable;
    private List<FilterCriteria> filter;
    private String query;
}
