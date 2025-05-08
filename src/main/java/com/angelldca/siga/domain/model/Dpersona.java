package com.angelldca.siga.domain.model;


import com.angelldca.siga.infrastructure.adapter.out.persistence.dimagenfacial.DimagenfacialEntity;
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
    private Long id;
    private String area;
    private String rolinstitucional;
    private String nombre;
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
    private Boolean isDelete = false;
    private Empresa empresa;
}
