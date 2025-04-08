package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.purta_persona.CreatePuertaPersonaCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.puerta_persona.PuertaPersonaCrudPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.common.response.PuertaPersonaResponse;
import com.angelldca.siga.common.response.PuertaResponse;
import com.angelldca.siga.domain.model.Dpersona;
import com.angelldca.siga.domain.model.Puerta;
import com.angelldca.siga.domain.model.PuertaPersona;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta.PuertaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta.PuertaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta_persona.PuertaPersonaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta_persona.PuertaPersonaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
public class PuertaPersonaService implements
        CreateUseCase<PuertaPersona, CreatePuertaPersonaCommand>,
        UpdateUseCase<PuertaPersona, CreatePuertaPersonaCommand,UUID>,
        DeleteUseCase<PuertaPersona, UUID>,
        GetUseCase<UUID>,
        ListUseCase {

    private final PuertaPersonaCrudPort puertaPersonaCrudPort;
    private final GetPort<Dpersona,Long> personaGetPort;
    private final GetPort<Puerta,Long> puertaGetPort;

    public PuertaPersonaService(
            @Qualifier("puertaPersonaPersistenceAdapter") PuertaPersonaCrudPort puertaPersonaCrudPort,
            @Qualifier("personaPersistenceAdapter")GetPort<Dpersona, Long> personaGetPort,
            @Qualifier("puertaPersistenceAdapter")GetPort<Puerta, Long> puertaGetPort) {
        this.puertaPersonaCrudPort = puertaPersonaCrudPort;
        this.personaGetPort = personaGetPort;
        this.puertaGetPort = puertaGetPort;
    }


    @Override
    public PuertaPersona create(CreatePuertaPersonaCommand command) {
        Dpersona dpersona = this.personaGetPort.obtenerPorId(command.getPersonaId());
        Puerta puerta = this.puertaGetPort.obtenerPorId(command.getPuertaId());
        PuertaPersona puertaPersona = new PuertaPersona(UUID.randomUUID(),dpersona,puerta);
        return this.puertaPersonaCrudPort.save(puertaPersona);
    }

    @Override
    public PuertaPersona delete(UUID id) {
        return this.puertaPersonaCrudPort.delete(id);
    }

    @Override
    public PuertaPersona update(CreatePuertaPersonaCommand command, UUID id) {
        Dpersona dpersona = this.personaGetPort.obtenerPorId(command.getPersonaId());
        Puerta puerta = this.puertaGetPort.obtenerPorId(command.getPuertaId());
        PuertaPersona puertaPersona = this.puertaPersonaCrudPort.obtenerPorId(id);

        puertaPersona.setPuerta(puerta);
        puertaPersona.setPersona(dpersona);
        return this.puertaPersonaCrudPort.save(puertaPersona);
    }

    @Override
    public IResponse getById(UUID id) {
        return new PuertaPersonaResponse(this.puertaPersonaCrudPort.obtenerPorId(id));
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<PuertaPersonaEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<PuertaPersonaEntity> data = this.puertaPersonaCrudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<PuertaPersonaEntity> data) {
        List<PuertaPersonaResponse> response = new ArrayList<>();
        for (PuertaPersonaEntity p : data.getContent()) {
            response.add(new PuertaPersonaResponse(PuertaPersonaMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(response, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
