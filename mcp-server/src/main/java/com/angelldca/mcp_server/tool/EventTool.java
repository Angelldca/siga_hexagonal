package com.angelldca.mcp_server.tool;


import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.ZonaEvento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zonaEvento.ZonaEventoMapper;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.EventReadDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.ZonaEventoReadDataJPARepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTool{
    private static final Logger log = LoggerFactory.getLogger(EventTool.class);

   /*
   *  private final EventReadDataJPARepository queryEvento;
    private final ZonaEventoReadDataJPARepository queryZonaEvento;


    public EventTool(EventReadDataJPARepository query,ZonaEventoReadDataJPARepository queryZonaEvento) {
        this.queryEvento = query;
        this.queryZonaEvento = queryZonaEvento;
    }
    @Tool(description = "Devuelve un saludo")
    public String saludar() {
        return "¡Hola desde Claude + Spring Boot!";
    }
    @Tool(description = "Get a list of all event")
    public List<Evento> getEventos() {
        return this.queryEvento.findAll().stream().map(
                EventoMapper::entityToDomain
        ).toList();
    }

    @Tool(description = "Get a list of all event and zona")
    public List<ZonaEvento> getZonaEvento() {
        return this.queryZonaEvento.findAll().stream().map(
                ZonaEventoMapper::entityToDomain
        ).toList();
    }
   * */
   @Tool(description = "Devuelve un saludo")
   public String saludar() {
       return "¡Hola desde Claude + Spring Boot!";
   }
}
