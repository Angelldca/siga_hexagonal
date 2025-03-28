package com.angelldca.siga.domain.utils.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterCriteria implements Serializable {
    private String key;
    private SearchOperation operator;
    private Object value;
    private LogicalOperation logicalOperation = LogicalOperation.AND;
}
