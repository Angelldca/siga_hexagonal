package com.angelldca.mcp_server.tool;



import com.angelldca.mcp_server.persistence.entities.EventoEntity;
import com.angelldca.mcp_server.persistence.entities.ZonaEntity;
import com.angelldca.mcp_server.persistence.entities.ZonaEventoEntity;
import com.angelldca.mcp_server.persistence.repository.EventReadDataJPARepository;
import com.angelldca.mcp_server.persistence.repository.ZonaEventoReadDataJPARepository;
import com.angelldca.mcp_server.persistence.repository.ZonaReadDataJPARepository;
import com.angelldca.siga.application.service.EventService;
import com.angelldca.siga.application.service.PersonaService;
import com.angelldca.siga.application.service.ZonaEventoService;
import com.angelldca.siga.application.service.ZonaService;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.PaginatedResponse;
import jakarta.annotation.PostConstruct;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
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

@Service
public class EventTool{
    private static final Logger log = LoggerFactory.getLogger(EventTool.class);


     private final EventService eventService;
     private final ZonaEventoService zonaEventoService;
    private final ZonaService zonaService;
    private final PersonaService personaService;

    public EventTool(EventService eventService,
                     ZonaEventoService zonaEventoService,
                     ZonaService zonaService,
                     PersonaService personaService
    ) {
        this.eventService = eventService;
        this.zonaEventoService = zonaEventoService;
        this.zonaService = zonaService;
        this.personaService = personaService;
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
       return "¡Hola te saluda el sistema de control de acceso";
   }
    public InputStream cargarManual() throws IOException {
        ClassPathResource resource = new ClassPathResource("manual.pdf");
        return resource.getInputStream();
    }
    public String leerTextoDelManual() throws IOException {
        try (InputStream is = new ClassPathResource("manual/1747406168684-Manual PWM.pdf").getInputStream();
             PDDocument document = PDDocument.load(is)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
    public String extraerTexto(InputStream pdfStream) throws IOException {
        try (PDDocument document = PDDocument.load(pdfStream)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
    @Tool(name = "manual_query", description = "Responde preguntas sobre el manual")
    public String responderSobreManual(
            @ToolParam(description = "El nombre del manual, ejemplo: gas.pdf") String nombreManual,
            @ToolParam(description = "La pregunta del usuario") String pregunta
    ) throws IOException {
        String contenidoManual = this.leerTextoDelManual();
        if (contenidoManual.isEmpty())return "Lo siento, no encontré información relevante.";
        return contenidoManual;
    }
}
