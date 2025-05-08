package com.angelldca.siga.application.port.out;


import java.util.List;

public interface ListByIdsPort <T,I> {
    List<T> listByIds(List<I> ids);
}
