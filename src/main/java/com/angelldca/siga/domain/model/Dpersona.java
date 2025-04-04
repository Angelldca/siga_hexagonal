package com.angelldca.siga.domain.model;


import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.EStatus;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dpersona {
    private Long idciudadano;
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
    private Empresa empresa;
}
