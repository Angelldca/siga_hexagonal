package com.angelldca.siga.application.port.in.command.persona;



import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonaCommand {
    private String area;
    private String rolinstitucional;
    private String primernombre;
    private String segundonombre;
    private String primerapellido;
    private String segundoapellido;
    private String solapin;
    private String carnetidentidad;
    private String provincia;
    private String municipio;
    private String sexo;
    private Boolean residente;
    private LocalDateTime fechanacimiento;
    private String idexpediente;
    private String codigobarra;
    private EStatus estado;
    private UUID empresaId;
}
