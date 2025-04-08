package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.persona.CreatePersonaCommand;
import com.angelldca.siga.application.port.in.command.persona.UpdatePersonacommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.persona.PersonaCrudPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.common.response.PersonaResponse;
import com.angelldca.siga.common.response.PuertaResponse;
import com.angelldca.siga.domain.model.Dpersona;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.EStatus;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta.PuertaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta.PuertaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@UseCase
public class PersonaService implements
        CreateUseCase<Dpersona, CreatePersonaCommand>,
        UpdateUseCase<Dpersona, UpdatePersonacommand,Long>,
        DeleteUseCase<Dpersona,Long>,
        GetUseCase<Long>,
        ListUseCase {

    private final PersonaCrudPort personaCrudPort;
    private final GetPort<Empresa, UUID> empresaGetPort;

    public PersonaService(
            @Qualifier("personaPersistenceAdapter")PersonaCrudPort personaCrudPort,
            @Qualifier("empresaPersistenceAdapter") GetPort<Empresa, UUID> empresaGetPort) {
        this.personaCrudPort = personaCrudPort;
        this.empresaGetPort = empresaGetPort;
    }

    @Override
    public Dpersona create(CreatePersonaCommand command) {
        Empresa empresa = this.empresaGetPort.obtenerPorId(command.getEmpresaId());
        Dpersona persona = new Dpersona(
                null,
                command.getArea(), command.getRolinstitucional(),
                command.getPrimernombre(), command.getSegundonombre(),
                command.getPrimerapellido(), command.getSegundoapellido(), command.getSolapin(),
                command.getCarnetidentidad(), command.getProvincia(), command.getMunicipio(), command.getSexo(),
                command.getResidente(),command.getFechanacimiento(),command.getIdexpediente(),
                command.getCodigobarra(),command.getEstado(),empresa
        );

        return this.personaCrudPort.save(persona);
    }

    @Override
    public Dpersona delete(Long id) {
        Dpersona dpersona = this.personaCrudPort.obtenerPorId(id);
        dpersona.setEstado(EStatus.INACTIVO);
        this.personaCrudPort.save(dpersona);
        return dpersona;
    }

    @Override
    public Dpersona update(UpdatePersonacommand command, Long id) {

        Dpersona persona = this.personaCrudPort.obtenerPorId(id);
        persona.setEstado(command.getEstado());
        persona.setArea(command.getArea());
        persona.setCarnetidentidad(command.getCarnetidentidad());
        persona.setCodigobarra(command.getCodigobarra());
        persona.setSolapin(command.getSolapin());
        persona.setProvincia(command.getProvincia());
        persona.setMunicipio(command.getMunicipio());
        persona.setPrimernombre(command.getPrimernombre());
        persona.setSegundonombre(command.getSegundonombre());
        persona.setPrimerapellido(command.getPrimerapellido());
        persona.setSegundoapellido(command.getSegundoapellido());
        persona.setFechanacimiento(command.getFechanacimiento());
        persona.setIdexpediente(command.getIdexpediente());
        persona.setResidente(command.getResidente());
        persona.setSexo(command.getSexo());
        persona.setRolinstitucional(command.getRolinstitucional());

        return this.personaCrudPort.save(persona);
    }

    @Override
    public IResponse getById(Long id) {
        return new PersonaResponse(this.personaCrudPort.obtenerPorId(id));
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<DpersonaEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<DpersonaEntity> data = this.personaCrudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<DpersonaEntity> data) {
        List<PersonaResponse> response = new ArrayList<>();
        for (DpersonaEntity p : data.getContent()) {
            response.add(new PersonaResponse(DpersonaMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(response, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
