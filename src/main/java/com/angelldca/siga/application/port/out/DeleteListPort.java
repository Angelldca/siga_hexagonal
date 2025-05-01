package com.angelldca.siga.application.port.out;

import java.util.List;

public interface DeleteListPort <T>{
    void deleteList(List<T> id);
}
