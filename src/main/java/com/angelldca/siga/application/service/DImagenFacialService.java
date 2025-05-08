package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.dimagen.CreateImagenCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.ImagenFacialResponse;
import com.angelldca.siga.domain.model.Dimagenfacial;
import com.angelldca.siga.domain.model.Dpersona;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Base64;

@UseCase
public class DImagenFacialService implements
        CreateUseCase<Dimagenfacial, CreateImagenCommand>,
        UpdateUseCase<Dimagenfacial, CreateImagenCommand,Long>,
        GetUseCase<Long>{

    private final GetPort<Dpersona, Long> dpersonaLongGetPort;
    private final GetPort<Dimagenfacial, Long> dimagenfacialLongGetPort;
    private final SavePort<Dimagenfacial> savePort;

    public DImagenFacialService(
            @Qualifier("personaPersistenceAdapter")GetPort<Dpersona, Long> dpersonaLongGetPort,
            @Qualifier("imagenPersistenceAdapter")GetPort<Dimagenfacial, Long> dimagenfacialLongGetPort,
            @Qualifier("imagenPersistenceAdapter")SavePort<Dimagenfacial> savePort) {
        this.dpersonaLongGetPort = dpersonaLongGetPort;
        this.dimagenfacialLongGetPort = dimagenfacialLongGetPort;
        this.savePort = savePort;
    }

    @Override
    public Dimagenfacial create(CreateImagenCommand command) {
        byte[] fotoBytes = Base64.getDecoder().decode(command.getFoto());

        Dimagenfacial dimagenfacial = new Dimagenfacial(
                null,
                this.dpersonaLongGetPort.obtenerPorId(command.getPersonaId()),
                fotoBytes,
                true

        );

        return this.savePort.save(dimagenfacial);
    }

    @Override
    public Dimagenfacial update(CreateImagenCommand command, Long id) {
        byte[] fotoBytes = Base64.getDecoder().decode(command.getFoto());
        Dimagenfacial dimagenfacial = this.dimagenfacialLongGetPort.obtenerPorId(id);
        dimagenfacial.setFoto(fotoBytes);
        return this.savePort.save(dimagenfacial);
    }

    @Override
    public IResponse getById(Long id) {
        return new ImagenFacialResponse(this.dimagenfacialLongGetPort.obtenerPorId(id));
    }
}
