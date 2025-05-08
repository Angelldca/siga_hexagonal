package com.angelldca.siga.application.port.out;

import java.util.List;

public interface SaveAllPort <T>{

    void saveAllPort(List<T> elements);
}
