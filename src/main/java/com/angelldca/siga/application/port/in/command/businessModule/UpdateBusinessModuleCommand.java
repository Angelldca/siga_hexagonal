package com.angelldca.siga.application.port.in.command.businessModule;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBusinessModuleCommand {
    private UUID empresaId;
    private Long modulesId;
}
