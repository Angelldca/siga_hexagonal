package com.angelldca.siga.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zona {
    private Long id;
    private String nombre;
    private Empresa empresa;
    private Boolean isDelete;
}
