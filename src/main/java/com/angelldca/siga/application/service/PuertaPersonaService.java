package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.purta_persona.CreatePuertaPersonaCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListByIdsPort;
import com.angelldca.siga.application.port.out.SaveAllPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.application.port.out.persona.PersonaOptionalPort;
import com.angelldca.siga.application.port.out.puerta_persona.DeletePortByPersonaId;
import com.angelldca.siga.application.port.out.puerta_persona.PuertaPersonaCrudPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.common.response.PuertaPersonaResponse;
import com.angelldca.siga.domain.model.*;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta_persona.PuertaPersonaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta_persona.PuertaPersonaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
public class PuertaPersonaService implements
        CreateUseCase<Dpersona, CreatePuertaPersonaCommand>,
        UpdateUseCase<Dpersona, CreatePuertaPersonaCommand,Long>,
        DeleteUseCase<PuertaPersona, UUID>,
        GetUseCase<UUID>,
        ListUseCase {

    private final PuertaPersonaCrudPort puertaPersonaCrudPort;
    private final GetPort<Dpersona,Long> personaGetPort;
    private final GetPort<Puerta,Long> puertaGetPort;
    private final ListByIdsPort<Puerta,Long> listByIdsPort;
    private final SaveAllPort<PuertaPersona> saveAllPort;
    private final DeletePortByPersonaId deletePortByPersonaId;
    private final GetPort<Empresa,UUID> empresaGetPort;
    private final PersonaOptionalPort personaOptionalPort;
    private final SavePort<Dpersona> savePersonaPort;

    public PuertaPersonaService(
            @Qualifier("puertaPersonaPersistenceAdapter") PuertaPersonaCrudPort puertaPersonaCrudPort,
            @Qualifier("personaPersistenceAdapter")GetPort<Dpersona, Long> personaGetPort,
            @Qualifier("puertaPersistenceAdapter")GetPort<Puerta, Long> puertaGetPort,
            @Qualifier("puertaPersistenceAdapter")ListByIdsPort<Puerta, Long> listByIdsPort,
            @Qualifier("puertaPersonaPersistenceAdapter")SaveAllPort<PuertaPersona> saveAllPort,
            @Qualifier("puertaPersonaPersistenceAdapter") DeletePortByPersonaId deletePortByPersonaId,
            @Qualifier("empresaPersistenceAdapter")GetPort<Empresa, UUID> empresaGetPort, @Qualifier("personaPersistenceAdapter")PersonaOptionalPort personaOptionalPort,
            @Qualifier("personaPersistenceAdapter")SavePort<Dpersona> savePersonaPort) {
        this.puertaPersonaCrudPort = puertaPersonaCrudPort;
        this.personaGetPort = personaGetPort;
        this.puertaGetPort = puertaGetPort;
        this.listByIdsPort = listByIdsPort;
        this.saveAllPort = saveAllPort;
        this.deletePortByPersonaId = deletePortByPersonaId;
        this.empresaGetPort = empresaGetPort;
        this.personaOptionalPort = personaOptionalPort;
        this.savePersonaPort = savePersonaPort;
    }


    @Override
    public Dpersona create(CreatePuertaPersonaCommand command) {
        Dpersona dpersona = this.personaGetPort.obtenerPorId(command.getPersonaId());
        if(command.getPuertaIds().isEmpty()) return dpersona;
        List<Puerta> puertas = this.listByIdsPort.listByIds(command.getPuertaIds());
        List<PuertaPersona> puertaPersonas = puertas
                .stream().map(
                        p -> new PuertaPersona(
                                UUID.randomUUID(),dpersona,p
                        )
                ).toList();
        this.saveAllPort.saveAllPort(puertaPersonas);
        return dpersona;
    }

    @Override
    public PuertaPersona delete(UUID id) {
        return this.puertaPersonaCrudPort.delete(id);
    }

    @Override
    @Transactional
    public Dpersona update(CreatePuertaPersonaCommand command, Long id) {
        Dpersona dpersona = this.personaGetPort.obtenerPorId(id);
        deletePortByPersonaId.deleteByPersonaId(id);
        if(command.getPuertaIds().isEmpty()) return dpersona;
        List<Puerta> puertas = listByIdsPort.listByIds(command.getPuertaIds());

        List<PuertaPersona> nuevos = puertas.stream()
                .map(p -> new PuertaPersona(UUID.randomUUID(), dpersona, p))
                .toList();
        this.saveAllPort.saveAllPort(nuevos);
        return dpersona;
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
