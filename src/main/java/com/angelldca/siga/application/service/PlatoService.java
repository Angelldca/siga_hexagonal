package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.plato.*;
import com.angelldca.siga.application.port.out.DeletePlatoPort;
import com.angelldca.siga.application.port.out.GetPlatoPort;
import com.angelldca.siga.application.port.out.ListPlatosPort;
import com.angelldca.siga.application.port.out.SavePlatoPort;
import com.angelldca.siga.common.UseCase;
import com.angelldca.siga.domain.model.Plato;

@UseCase
public class PlatoService implements CreatePlatoUseCase, UpdatePlatoUseCase, DeletePlatoUseCase {

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
}
