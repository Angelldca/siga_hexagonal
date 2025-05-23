package com.angelldca.siga.common.criteria;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SearchRequest {
    private List<FilterCriteria> filter;
    private String query;
    private Integer pageSize;
    private Integer page;
    private String sortBy;
    private SortTypeEnum sortType;
}
