package com.angelldca.mcp_server.tool;



import com.angelldca.mcp_server.persistence.entities.EventoEntity;
import com.angelldca.mcp_server.persistence.entities.ZonaEntity;
import com.angelldca.mcp_server.persistence.entities.ZonaEventoEntity;
import com.angelldca.mcp_server.persistence.repository.EventReadDataJPARepository;
import com.angelldca.mcp_server.persistence.repository.ZonaEventoReadDataJPARepository;
import com.angelldca.mcp_server.persistence.repository.ZonaReadDataJPARepository;
import com.angelldca.siga.application.service.*;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Manual;
import com.angelldca.siga.infrastructure.adapter.out.persistence.manual.ManualEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class EventTool{
    private static final Logger log = LoggerFactory.getLogger(EventTool.class);
    @Value("${manual.storage.path}")
    private String storagePath;

     private final EventService eventService;
     private final ZonaEventoService zonaEventoService;
    private final ZonaService zonaService;
    private final PersonaService personaService;
    private final ManualService manualService;

    public EventTool(EventService eventService,
                     ZonaEventoService zonaEventoService,
                     ZonaService zonaService,
                     PersonaService personaService,
                     ManualService manualService
    ) {
        this.eventService = eventService;
        this.zonaEventoService = zonaEventoService;
        this.zonaService = zonaService;
        this.personaService = personaService;
        this.manualService = manualService;
    }
    @Tool(description = "Devuelve una lista de los eventos paginados y con filtros si los proporciona el usuario")
    public PaginatedResponse getEventos(
            @ToolParam(description = "Número de página (empezando desde 0)") int page,
            @ToolParam(description = "Tamaño de página") int size,
            @ToolParam(description = "Lista de filtros opcionales") List<FilterCriteria> filters) {

        Pageable pageable = PageRequest.of(page, size);
        List<FilterCriteria> criteriaList = filters.stream()
                .map(f -> new FilterCriteria(f.getKey(), f.getOperator(), f.getValue(), f.getLogicalOperation()))
                .toList();

        return this.eventService.search(pageable, criteriaList);
    }

    @Tool(description = "Devuelve una lista de los eventos y sus respectivas zonas o areas paginados " +
            "y con filtros si los proporciona el usuario")
    public PaginatedResponse getZonaEventos(  @ToolParam(description = "Número de página (empezando desde 0)") int page,
                                                   @ToolParam(description = "Tamaño de página") int size,
                                                   @ToolParam(description = "Lista de filtros opcionales") List<FilterCriteria> filters) {

        Pageable pageable = PageRequest.of(page, size);
        List<FilterCriteria> criteriaList = filters.stream()
                .map(f -> new FilterCriteria(f.getKey(), f.getOperator(), f.getValue(), f.getLogicalOperation()))
                .toList();

        return this.zonaEventoService.search(pageable, criteriaList);
    }
    @Tool(description = "Devuelve una lista de las personas paginados " +
            "y con filtros si los proporciona el usuario")
    public PaginatedResponse getPersonas(  @ToolParam(description = "Número de página (empezando desde 0)") int page,
                                              @ToolParam(description = "Tamaño de página") int size,
                                              @ToolParam(description = "Lista de filtros opcionales") List<FilterCriteria> filters) {

        Pageable pageable = PageRequest.of(page, size);
        List<FilterCriteria> criteriaList = filters.stream()
                .map(f -> new FilterCriteria(f.getKey(), f.getOperator(), f.getValue(), f.getLogicalOperation()))
                .toList();

        return this.personaService.search(pageable, criteriaList);
    }
    @Tool(description = "Devuelve una lista de las zonas o areas paginados " +
            "y con filtros si los proporciona el usuario")
    public PaginatedResponse getZonas(  @ToolParam(description = "Número de página (empezando desde 0)") int page,
                                              @ToolParam(description = "Tamaño de página") int size,
                                              @ToolParam(description = "Lista de filtros opcionales") List<FilterCriteria> filters) {

        Pageable pageable = PageRequest.of(page, size);
        List<FilterCriteria> criteriaList = filters.stream()
                .map(f -> new FilterCriteria(f.getKey(), f.getOperator(), f.getValue(), f.getLogicalOperation()))
                .toList();

        return this.zonaService.search(pageable, criteriaList);
    }

   @Tool(name="toolSaludo",description = "Devuelve un saludo")
   public String saludar() {
       return "¡Hola te saluda el sistema de gestion de manuales";
   }

    public String leerTextoDelPDF(String pathDentroDeResources) {
        try (InputStream is = new ClassPathResource(pathDentroDeResources).getInputStream();
             PDDocument document = PDDocument.load(is)) {

            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);

        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el PDF desde recursos: " + pathDentroDeResources, e);
        }
    }
    @Tool(name = "manual_query", description = "Responde preguntas sobre un determinado manual")
    public String responderSobreManual(
            @ToolParam(description = "Nombre parcial del manual") String nombreManual,
            @ToolParam(description = "Palabras claves incluyendo el nombre parcial del manual") List<String> keywords,
            @ToolParam(description = "Pregunta del usuario") String pregunta
    ) throws IOException {
        List<Manual> manuales = manualService.buscarManualMasRelevante(nombreManual,keywords);
        if (manuales.isEmpty()) {
            return "No encontré manuales con el nombre: '" + nombreManual + "'.";
        }
        Manual manual = manuales.get(0);
        String contenido = leerTextoDelPDF("manual/"+manual.getNombre()+".pdf");//
        // TODO: que hacer si  no encuentra el pdf
        return contenido;

    }
    @Tool(description = "Devuelve una lista de los manuales paginados " +
            "y con filtros si los proporciona el usuario")
    public PaginatedResponse getManuales(  @ToolParam(description = "Número de página (empezando desde 0)") int page,
                                           @ToolParam(description = "Tamaño de página") int size,
                                           @ToolParam(description = "Lista de filtros opcionales") List<FilterCriteria> filters) {

        Pageable pageable = PageRequest.of(page, size);
        List<FilterCriteria> criteriaList = filters.stream()
                .map(f -> new FilterCriteria(f.getKey(), f.getOperator(), f.getValue(), f.getLogicalOperation()))
                .toList();

        return this.manualService.search(pageable, criteriaList);
    }
}
