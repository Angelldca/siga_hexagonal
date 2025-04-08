package com.angelldca.siga.application.port.out.acceso;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.Acceso;
import com.angelldca.siga.infrastructure.adapter.out.persistence.acceso.AccesoEntity;


public interface AccesoCrudPort extends DeletePort<Acceso, Long>, GetPort<Acceso,Long>,
        ListPort<AccesoEntity>, SavePort<Acceso> {
}
