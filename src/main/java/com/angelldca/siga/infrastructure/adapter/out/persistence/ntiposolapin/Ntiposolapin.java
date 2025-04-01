package com.angelldca.siga.infrastructure.adapter.out.persistence.ntiposolapin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ntiposolapin")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ntiposolapin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtiposolapin")
    private Long idtiposolapin;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "categoria")
    private String categoria;
}