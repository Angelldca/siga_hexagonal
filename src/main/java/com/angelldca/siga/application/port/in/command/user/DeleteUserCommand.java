package com.angelldca.siga.application.port.in.command.user;

import lombok.Getter;

import java.util.UUID;

@Getter

public class DeleteUserCommand {
    private UUID id;
}
