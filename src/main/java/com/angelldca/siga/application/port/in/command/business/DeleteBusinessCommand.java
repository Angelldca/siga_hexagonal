package com.angelldca.siga.application.port.in.command.business;


import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteBusinessCommand {
    private UUID id;
}
