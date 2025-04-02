package com.angelldca.siga.application.port.out;

public interface DeletePort<T,I> {
    T delete(I id);
}
