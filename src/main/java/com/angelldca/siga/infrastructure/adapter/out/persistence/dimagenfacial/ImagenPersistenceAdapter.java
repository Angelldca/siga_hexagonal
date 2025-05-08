package com.angelldca.siga.infrastructure.adapter.out.persistence.dimagenfacial;


import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Dimagenfacial;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaMapper;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.ImagenWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.ImagenReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

@PersistenceAdapter
@Qualifier("imagenPersistenceAdapter")
public class ImagenPersistenceAdapter implements GetPort<Dimagenfacial,Long>, SavePort<Dimagenfacial> {

   private final ImagenReadDataJPARepository query;
   private final ImagenWriteDataJPARepository command;

    public ImagenPersistenceAdapter(ImagenReadDataJPARepository query, ImagenWriteDataJPARepository command) {
        this.query = query;
        this.command = command;
    }

    @Override
    public Dimagenfacial obtenerPorId(Long id) {
        Dimagenfacial imagen = query.findByPersona_Id(id)
                .map(DimagenfacialMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Persona"));

       return imagen;
    }

    @Override
    public Dimagenfacial save(Dimagenfacial domain) {
        DimagenfacialEntity entity = this.command.save(DimagenfacialMapper.domainToEntity(domain));
        return DimagenfacialMapper.entityToDomain(entity);
    }
}
