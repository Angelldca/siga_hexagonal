package com.angelldca.siga.infrastructure.adapter.out.persistence.usuario;


import com.angelldca.siga.application.port.out.user.GetByEmailPort;
import com.angelldca.siga.application.port.out.user.UserCrudPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;

import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.UserWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.UserReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


import java.util.UUID;

@PersistenceAdapter
@Qualifier("userPersistenceAdapter")
public class UserPersistenceAdapter implements UserCrudPort, GetByEmailPort {


    private final UserReadDataJPARepository query;
    private final UserWriteDataJPARepository command;

    public UserPersistenceAdapter(UserReadDataJPARepository query, UserWriteDataJPARepository command) {
        this.query = query;
        this.command = command;
    }

    @Override
    public User delete(UUID id) {
        User user = obtenerPorId(id);
        UserEntity entity = UsuarioMapper.domainToEntity(user);
        this.command.delete(entity);
        return user;
    }

    @Override
    public User obtenerPorId(UUID id) {
        User user = this.query.findById(id)
                .map(UsuarioMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Usuario"));
        return user;
    }

    @Override
    public Page<UserEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public User save(User entity) {
        UserEntity user = this.command.save(UsuarioMapper.domainToEntity(entity));
        return UsuarioMapper.entityToDomain(user);
    }

    @Override
    public User findByEmail(String email) {
        return query.findByEmail(email)
                .map(UsuarioMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("email","Usuario"));
    }


}
