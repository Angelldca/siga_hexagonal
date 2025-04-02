package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.plato.*;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.common.response.PlatoResponse;
import com.angelldca.siga.domain.rule.Plato.PlatoNameNotNullRule;
import com.angelldca.siga.domain.rule.RulesChecker;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@UseCase

public class PlatoService implements
        CreateUseCase<Plato,CreatePlatoCommand>,
        UpdateUseCase<Plato,UpdatePlatoCommand,Long>,
        DeleteUseCase<Plato,Long>,
        GetUseCase<Long>,
        ListUseCase {

    private final DeletePort<Plato,Long> deletePlatoPort;
    private final GetPort<Plato,Long> getPlatoPort;
    private final ListPort<PlatoEntity> listPlatosPort;
    private final SavePort<Plato> savePlatoPort;

    public PlatoService(
            @Qualifier("platoPersistenceAdapter") DeletePort<Plato,Long> deletePlatoPort,
            @Qualifier("platoPersistenceAdapter") GetPort<Plato,Long> getPlatoPort,
            @Qualifier("platoPersistenceAdapter") ListPort<PlatoEntity> listPlatosPort,
            @Qualifier("platoPersistenceAdapter") SavePort<Plato> savePlatoPort
            ) {
        this.deletePlatoPort = deletePlatoPort;
        this.getPlatoPort = getPlatoPort;
        this.listPlatosPort = listPlatosPort;
        this.savePlatoPort = savePlatoPort;
    }

    @Override
    public Plato create(CreatePlatoCommand command) {
        RulesChecker.checkRule(new PlatoNameNotNullRule(command.getNombre()));
        Plato entity = new Plato(
                null,
                command.getNombre(),
                command.getPrecio(),
                command.getMedida(),
                command.getDisponible()
        );

        return this.savePlatoPort.save(entity);
    }

    @Override
    public Plato delete(Long id) {
       return this.deletePlatoPort.delete(id);
    }

    @Override
    public Plato update(UpdatePlatoCommand command, Long id) {
       Plato entity =  this.getPlatoPort.obtenerPorId(id);
       entity.setMedida(command.getMedida());
       entity.setNombre(command.getNombre());
       entity.setPrecio(command.getPrecio());
       entity.setDisponible(command.getDisponible());
       return this.savePlatoPort.save(entity);
    }

    @Override
    public PlatoResponse getById(Long id) {
        Plato entity =  this.getPlatoPort.obtenerPorId(id);
        return new PlatoResponse(entity);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<PlatoEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<PlatoEntity> data = this.listPlatosPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<PlatoEntity> data) {
        List<PlatoResponse> platoResponses = new ArrayList<>();
        for (PlatoEntity p : data.getContent()) {
            platoResponses.add(new PlatoResponse(PlatoMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(platoResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
