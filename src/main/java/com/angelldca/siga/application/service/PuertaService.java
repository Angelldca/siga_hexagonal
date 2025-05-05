package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteListUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.Puerta.CreatePuertaCommand;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.DeleteListCommand;
import com.angelldca.siga.application.port.out.DeleteListPort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.puerta.PuertaCrudPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;

import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.common.response.PuertaResponse;
import com.angelldca.siga.domain.model.Puerta;
import com.angelldca.siga.domain.model.Zona;
;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta.PuertaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta.PuertaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@UseCase
public class PuertaService implements
        CreateUseCase<Puerta, CreatePuertaCommand>,
        UpdateUseCase<Puerta, CreatePuertaCommand,Long>,
        DeleteUseCase<Puerta,Long>,
        GetUseCase<Long>, DeleteListUseCase<Long>,
        ListUseCase {

    private final PuertaCrudPort puertaCrudPort;
    private final GetPort<Zona,Long> zonaGetPort;
    private final DeleteListPort<Long> deleteListPort;

    public PuertaService(
            @Qualifier("puertaPersistenceAdapter")PuertaCrudPort puertaCrudPort,
            @Qualifier("zonaPersistenceAdapter")GetPort<Zona, Long> zonaGetPort,
            @Qualifier("puertaPersistenceAdapter")DeleteListPort<Long> deleteListPort) {
        this.puertaCrudPort = puertaCrudPort;
        this.zonaGetPort = zonaGetPort;

        this.deleteListPort = deleteListPort;
    }

    @Override
    public Puerta create(CreatePuertaCommand command) {
        Zona zona = this.zonaGetPort.obtenerPorId(command.getZonaId());
        Puerta puerta = new Puerta(null, command.getNombre(),zona,false);
        return this.puertaCrudPort.save(puerta);
    }

    @Override
    public Puerta delete(Long id) {
        return this.puertaCrudPort.delete(id);
    }

    @Override
    public Puerta update(CreatePuertaCommand command, Long id) {
        Zona zona = this.zonaGetPort.obtenerPorId(command.getZonaId());
        Puerta puerta = this.puertaCrudPort.obtenerPorId(id);
        puerta.setNombre(command.getNombre());
        puerta.setZona(zona);
        return this.puertaCrudPort.save(puerta);
    }

    @Override
    public IResponse getById(Long id) {
        return new PuertaResponse(this.puertaCrudPort.obtenerPorId(id));
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<PuertaEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<PuertaEntity> data = this.puertaCrudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<PuertaEntity> data) {
        List<PuertaResponse> response = new ArrayList<>();
        for (PuertaEntity p : data.getContent()) {
            response.add(new PuertaResponse(PuertaMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(response, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void deleteList(DeleteListCommand<Long> command) {
        this.deleteListPort.deleteList(command.getIds());
    }
}
