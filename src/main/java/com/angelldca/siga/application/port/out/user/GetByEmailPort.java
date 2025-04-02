package com.angelldca.siga.application.port.out.user;

import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UserEntity;

import java.util.Optional;

public interface GetByEmailPort {
    User findByEmail(String email);
}
