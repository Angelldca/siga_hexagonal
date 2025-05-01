package com.angelldca.siga.application.port.in.command;

import com.angelldca.siga.application.port.out.DeleteListCommand;

public interface DeleteListUseCase<T> {
    void deleteList(DeleteListCommand<T> command);
}
