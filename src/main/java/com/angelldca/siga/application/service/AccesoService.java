package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.acceso.CreateAccesoCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.acceso.AccesoCrudPort;
import com.angelldca.siga.application.port.out.menuEvento.GetOptionalMenuEventoPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.AccesoResponse;
import com.angelldca.siga.common.response.IResponse;

import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.*;
import com.angelldca.siga.infrastructure.adapter.out.persistence.acceso.AccesoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.acceso.AccesoMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
public class AccesoService implements
        CreateUseCase<Acceso, CreateAccesoCommand>,
        UpdateUseCase<Acceso, CreateAccesoCommand,Long>,
        DeleteUseCase<Acceso,Long>,
        GetUseCase<Long>,
        ListUseCase {

    private final AccesoCrudPort accesoCrudPort;
    private final GetPort<ZonaEvento, UUID> zonaEventoGetPort;
    private final GetPort<PuertaPersona,UUID> puertaPersonaGetPort;
    private final GetOptionalMenuEventoPort menuEventoGetPort;

    public AccesoService(
            @Qualifier("accesoPersistenceAdapter")AccesoCrudPort accesoCrudPort,
            @Qualifier("zonaEventoPersistenceAdapter")GetPort<ZonaEvento, UUID> zonaEventoGetPort,
            @Qualifier("puertaPersonaPersistenceAdapter") GetPort<PuertaPersona, UUID> puertaPersonaGetPort,
            @Qualifier("menuEventoPersistenceAdapter") GetOptionalMenuEventoPort menuEventoGetPort) {
        this.accesoCrudPort = accesoCrudPort;
        this.zonaEventoGetPort = zonaEventoGetPort;
        this.puertaPersonaGetPort = puertaPersonaGetPort;
        this.menuEventoGetPort = menuEventoGetPort;
    }

    @Override
    public Acceso create(CreateAccesoCommand command) {
        ZonaEvento zonaEvento = this.zonaEventoGetPort.obtenerPorId(command.getZonaEventoId());
        PuertaPersona puertaPersona = this.puertaPersonaGetPort.obtenerPorId(command.getPuertaPersonaId());
        MenuEvento menuEvento = this.menuEventoGetPort.loadMenuEvento(command.getMenuEventoId());

        return this.accesoCrudPort.save(new Acceso(
                null,puertaPersona,
                zonaEvento,menuEvento,
                zonaEvento.getEvento().getNombre(),
                null
        ));
    }

    @Override
    public Acceso delete(Long id) {
        return this.accesoCrudPort.delete(id);
    }

    @Override
    public Acceso update(CreateAccesoCommand command, Long id) {
        ZonaEvento zonaEvento = this.zonaEventoGetPort.obtenerPorId(command.getZonaEventoId());
        PuertaPersona puertaPersona = this.puertaPersonaGetPort.obtenerPorId(command.getPuertaPersonaId());
        MenuEvento menuEvento = this.menuEventoGetPort.loadMenuEvento(command.getMenuEventoId());
        Acceso acceso = this.accesoCrudPort.obtenerPorId(id);
        acceso.setNombreEvento(zonaEvento.getEvento().getNombre());
        acceso.setZonaEvento(zonaEvento);
        acceso.setMenuEvento(menuEvento);
        acceso.setPuertaPersona(puertaPersona);
        return this.accesoCrudPort.save(acceso);
    }

    @Override
    public IResponse getById(Long id) {
        return new AccesoResponse(this.accesoCrudPort.obtenerPorId(id));
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<AccesoEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<AccesoEntity> data = this.accesoCrudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<AccesoEntity> data) {
        List<AccesoResponse> accesoResponse = new ArrayList<>();
        for (AccesoEntity p : data.getContent()) {
            accesoResponse.add(new AccesoResponse(AccesoMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(accesoResponse, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
