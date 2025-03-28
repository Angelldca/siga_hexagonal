package com.angelldca.siga.infrastructure.adapter.out.persistence.plato;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;




@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "plato")
public class PlatoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String medida;
    private Boolean disponible;
}
