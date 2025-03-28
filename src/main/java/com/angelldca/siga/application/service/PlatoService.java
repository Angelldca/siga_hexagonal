package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.plato.*;
import com.angelldca.siga.application.port.in.query.plato.GetPlatoUseCase;
import com.angelldca.siga.application.port.in.query.plato.ListPlatoUseCase;
import com.angelldca.siga.application.port.in.query.response.PlatoResponse;
import com.angelldca.siga.domain.utils.response.GenericSpecificationsBuilder;
import com.angelldca.siga.domain.utils.response.PaginatedResponse;
import com.angelldca.siga.application.port.out.DeletePlatoPort;
import com.angelldca.siga.application.port.out.GetPlatoPort;
import com.angelldca.siga.application.port.out.ListPlatosPort;
import com.angelldca.siga.application.port.out.SavePlatoPort;
import com.angelldca.siga.common.UseCase;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.domain.utils.response.FilterCriteria;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@UseCase
public class PlatoService implements CreatePlatoUseCase, UpdatePlatoUseCase, DeletePlatoUseCase, GetPlatoUseCase, ListPlatoUseCase {

    private final DeletePlatoPort deletePlatoPort;
    private final GetPlatoPort getPlatoPort;
    private final ListPlatosPort listPlatosPort;
    private final SavePlatoPort savePlatoPort;

    public PlatoService(DeletePlatoPort deletePlatoPort, GetPlatoPort getPlatoPort, ListPlatosPort listPlatosPort, SavePlatoPort savePlatoPort) {
        this.deletePlatoPort = deletePlatoPort;
        this.getPlatoPort = getPlatoPort;
        this.listPlatosPort = listPlatosPort;
        this.savePlatoPort = savePlatoPort;
    }

    @Override
    public Plato create(CreatePlatoCommand command) {
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
    public void delete(Long id) {
        this.deletePlatoPort.delete(id);
    }

    @Override
    public Plato update(UpdatePlatoCommand command) {
       Plato entity =  this.getPlatoPort.obtenerPorId(command.getId());
       return this.savePlatoPort.save(entity);
    }

    @Override
    public PlatoResponse getPlatoById(Long id) {
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
