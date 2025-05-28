package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.manual.ManualCreateCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;

import com.angelldca.siga.application.port.out.manual.GetAllByNamePort;
import com.angelldca.siga.application.port.out.manual.GetByExcatNamePort;
import com.angelldca.siga.application.port.out.manual.GetByNombrePort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.ManualResponse;

import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Empresa;

import com.angelldca.siga.domain.model.Manual;

import com.angelldca.siga.infrastructure.adapter.out.persistence.manual.ManualEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.manual.ManualMapper;

import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@UseCase
public class ManualService {



    private final DeletePort<Manual,Long> deletePort;
    private final GetPort<Manual,Long> getPort;
    private final GetPort<Empresa, UUID> getPortEmpresa;
    private final ListPort<ManualEntity> listPort;
    private final SavePort<Manual> savePort;
    private final GetAllByNamePort getAllByNamePort;
    private final GetByNombrePort getByNombrePort;
    private final GetByExcatNamePort getByExcatNamePort;

    @Value("${manual.storage.path}")
    private String storagePath;

    public ManualService(
           @Qualifier("manualPersistenceAdapter") DeletePort<Manual,Long> deletePort,
           @Qualifier("manualPersistenceAdapter")GetPort<Manual,Long> getPort,
           @Qualifier("empresaPersistenceAdapter") GetPort<Empresa, UUID> getPortEmpresa,
           @Qualifier("manualPersistenceAdapter") ListPort<ManualEntity> listPort,
           @Qualifier("manualPersistenceAdapter") SavePort<Manual> savePort,
           GetAllByNamePort getAllByNamePort,
           GetByNombrePort getByNombrePort,
           GetByExcatNamePort getByExcatNamePort
    ) {
        this.deletePort = deletePort;
        this.getPort = getPort;
        this.getPortEmpresa = getPortEmpresa;
        this.listPort = listPort;
        this.savePort = savePort;
        this.getAllByNamePort=getAllByNamePort;
        this.getByNombrePort=getByNombrePort;
        this.getByExcatNamePort=getByExcatNamePort;
    }


    @Transactional
    public Manual create(ManualCreateCommand command,MultipartFile archivoPdf) {
        Empresa empresa = this.getPortEmpresa.obtenerPorId(command.getEmpresaId());
        String rutaGuardada;
        try {
            rutaGuardada = this.guardarManual(command.getNombre() + ".pdf", archivoPdf);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo del manual", e);
        }

        return this.savePort.save(
                new Manual(
                        null,
                        command.getNombre(),
                        command.getDescripcion(),
                        rutaGuardada,
                        empresa
                )
        );

    }

    @Transactional
    public Manual delete(Long id) {//TODO: mejorar excepciones
        Manual manual = this.getPort.obtenerPorId(id);
        Path ruta = Paths.get(manual.getFilePath());
        try {
            Files.deleteIfExists(ruta);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo eliminar el archivo del manual: " + e.getMessage(), e);
        }
        return this.deletePort.delete(id);
    }
    public Manual update(ManualCreateCommand command, Long id) {
        Manual manual = this.getPort.obtenerPorId(id);
        manual.setNombre(command.getNombre());
        manual.setDescripcion(command.getDescripcion());
        //manual.setFilePath("manuales/" + command.getNombre());
        return null;
    }


    public IResponse getById(Long id) {
        Manual entity =  this.getPort.obtenerPorId(id);
        return new ManualResponse(entity);
    }


    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<ManualEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<ManualEntity> data = this.listPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<ManualEntity> data) {
        List<ManualResponse> menuResponse = new ArrayList<>();
        for (ManualEntity p : data.getContent()) {
            menuResponse.add(new ManualResponse(ManualMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(menuResponse, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
    public String guardarManual(String nombreArchivo, MultipartFile archivo) throws IOException {
        Path rutaDestino = Paths.get(storagePath, nombreArchivo);
        Files.createDirectories(rutaDestino.getParent());
        Files.write(rutaDestino, archivo.getBytes());
        return rutaDestino.toString();
    }

    public Optional<ManualEntity> obtenerPorNombreExacto(String nombre) {
        return this.getByExcatNamePort.obtenerPorNombreExacto(nombre);
    }

    public List<ManualEntity> buscarPorNombreAproximado(String nombreParcial) {
        return this.getByNombrePort.buscarPorNombreAproximado(nombreParcial);
    }

    public List<String> obtenerTodosLosNombres() {
        return getAllByNamePort.obtenerTodosLosNombres();
    }
}
