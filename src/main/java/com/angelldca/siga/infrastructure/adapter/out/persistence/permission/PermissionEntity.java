package com.angelldca.siga.infrastructure.adapter.out.persistence.permission;


import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.modulos.ModuleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "permiso")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String description;
    private String action;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id")
    private ModuleEntity module;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
