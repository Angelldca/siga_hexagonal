package com.angelldca.siga.application.port.in.command;


public interface CreateUseCase<T,C> {
    T create(C command);
}
