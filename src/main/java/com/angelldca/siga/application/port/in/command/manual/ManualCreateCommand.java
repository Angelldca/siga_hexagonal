package com.angelldca.siga.application.port.in.command.manual;


import com.angelldca.siga.application.port.in.command.ICommand;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.domain.model.Manual;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManualCreateCommand implements ICommand {
    private String nombre;
    private String descripcion;
    private UUID empresaId;
}
