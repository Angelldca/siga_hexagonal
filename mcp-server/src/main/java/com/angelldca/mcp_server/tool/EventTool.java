package com.angelldca.mcp_server.tool;



import com.angelldca.mcp_server.persistence.entities.EventoEntity;
import com.angelldca.mcp_server.persistence.entities.ZonaEntity;
import com.angelldca.mcp_server.persistence.entities.ZonaEventoEntity;
import com.angelldca.mcp_server.persistence.repository.EventReadDataJPARepository;
import com.angelldca.mcp_server.persistence.repository.ZonaEventoReadDataJPARepository;
import com.angelldca.mcp_server.persistence.repository.ZonaReadDataJPARepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventTool{
    private static final Logger log = LoggerFactory.getLogger(EventTool.class);


     private final EventReadDataJPARepository queryEvento;
     private final ZonaEventoReadDataJPARepository queryZonaEvento;
    private final ZonaReadDataJPARepository queryZona;

    public EventTool(EventReadDataJPARepository query,
                     ZonaEventoReadDataJPARepository queryZonaEvento,
                            ZonaReadDataJPARepository queryZona
    ) {
        this.queryEvento = query;
        this.queryZonaEvento = queryZonaEvento;
        this.queryZona = queryZona;
    }
    @Tool(description = "Devuelve una lista de los eventos")
    public List<EventoEntity> getEventos() {
        return this.queryEvento.findAll();
    }

    @Tool(description = "Devuelve una lista de los eventos y sus respectivas zonas")
    public List<ZonaEventoEntity> getZonaEventos() {
        return this.queryZonaEvento.findAll();
    }
    @Tool(description = "Devuelve una lista de las zonas")
    public List<ZonaEntity> getZonas() {
        return this.queryZona.findAll();
    }

   @Tool(name="toolSaludo",description = "Devuelve un saludo")
   public String saludar() {
       return "¡Hola te saluda el sistema de control de acceso";
   }
}
