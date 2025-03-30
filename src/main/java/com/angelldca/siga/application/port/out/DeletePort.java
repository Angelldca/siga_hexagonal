package com.angelldca.siga.application.port.out;

public interface DeletePort<T> {
    T delete(Long id);
}
