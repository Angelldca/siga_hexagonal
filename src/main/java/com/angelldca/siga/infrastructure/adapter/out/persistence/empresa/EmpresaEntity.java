package com.angelldca.siga.infrastructure.adapter.out.persistence.empresa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table(name = "empresa")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpresaEntity {

    @Id
    private UUID id;

    private String nombre;
    private String logo;
    private String address;


    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime updatedAt;

}
