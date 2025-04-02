package com.angelldca.siga.application.port.out.user;


import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.User;

import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UserEntity;

import java.util.UUID;

public interface UserCrudPort extends DeletePort<User, UUID>, GetPort<User,UUID>,
        ListPort<UserEntity>, SavePort<User> {
}
