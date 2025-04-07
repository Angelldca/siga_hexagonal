package com.angelldca.siga.infrastructure.adapter.out.persistence.zonaEvento;


import com.angelldca.siga.application.port.out.zonaEvento.ZonaEventoCrudPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.domain.model.Zona;
import com.angelldca.siga.domain.model.ZonaEvento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UsuarioMapper;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.ZonaEventoWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.ZonaEventoReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@PersistenceAdapter
@Qualifier("zonaEventoPersistenceAdapter")
public class ZonaEventoPersistenceAdapter implements ZonaEventoCrudPort {

    private final ZonaEventoReadDataJPARepository query;
    private final ZonaEventoWriteDataJPARepository command;

    public ZonaEventoPersistenceAdapter(ZonaEventoReadDataJPARepository query, ZonaEventoWriteDataJPARepository command) {
        this.query = query;
        this.command = command;
    }

    @Override
    public ZonaEvento delete(UUID id) {
        ZonaEvento zona = this.obtenerPorId(id);
        this.command.delete(ZonaEventoMapper.domainToEntity(zona));
        return zona;
    }

    @Override
    public ZonaEvento obtenerPorId(UUID id) {
        ZonaEvento zonaEvento = this.query.findById(id)
                .map(ZonaEventoMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","ZonaEvento"));
        return zonaEvento;
    }

    @Override
    public Page<ZonaEventoEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public ZonaEvento save(ZonaEvento domain) {
        ZonaEventoEntity entity = this.command.save(ZonaEventoMapper.domainToEntity(domain));
        return ZonaEventoMapper.entityToDomain(entity);
    }
}
