package com.angelldca.siga.infrastructure.adapter.out.persistence.usuario;


import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_system")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    private UUID id;
    private String username;
    private String email;
    private String password;
    private String image;
    @Enumerated(EnumType.STRING)
    private EUserType type;

    @OneToMany(mappedBy = "user",orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<UserPermissionBusinessEntity> userPermissionBusinesses = new HashSet<>();
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
