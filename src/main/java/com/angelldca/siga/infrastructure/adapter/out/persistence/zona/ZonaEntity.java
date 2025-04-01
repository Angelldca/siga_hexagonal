package com.angelldca.siga.infrastructure.adapter.out.persistence.zona;

import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "zona")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaEntity empresa;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
