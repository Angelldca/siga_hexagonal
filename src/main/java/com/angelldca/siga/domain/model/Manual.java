package com.angelldca.siga.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manual {
    private Long id;
    private String nombre;
    private String descripcion;
    private String filePath;
    private Empresa empresa;
}
