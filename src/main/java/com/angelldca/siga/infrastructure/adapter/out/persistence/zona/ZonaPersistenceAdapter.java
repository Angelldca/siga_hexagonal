package com.angelldca.siga.infrastructure.adapter.out.persistence.zona;


import com.angelldca.siga.application.port.out.zona.ZonaCrudPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;

import com.angelldca.siga.domain.model.Zona;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.ZonaWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.ZonaReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@PersistenceAdapter
@Qualifier("zonaPersistenceAdapter")
public class ZonaPersistenceAdapter implements ZonaCrudPort {

    private final ZonaReadDataJPARepository query;
    private final ZonaWriteDataJPARepository command;

    public ZonaPersistenceAdapter(ZonaReadDataJPARepository query, ZonaWriteDataJPARepository command) {
        this.query = query;
        this.command = command;
    }

    @Override
    public Zona delete(Long id) {
        Zona zona = this.obtenerPorId(id);
        zona.setIsDelete(true);
        return this.save(zona);
    }

    @Override
    public Zona obtenerPorId(Long id) {
        Zona zona = this.query.findById(id)
                .map(ZonaMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Zona"));
        return zona;
    }

    @Override
    public Page<ZonaEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Zona save(Zona domain) {
        ZonaEntity zona = this.command.save(ZonaMapper.domainToEntity(domain));
        return ZonaMapper.entityToDomain(zona);
    }
}
