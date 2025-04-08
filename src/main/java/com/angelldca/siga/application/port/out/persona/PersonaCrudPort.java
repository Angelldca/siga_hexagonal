package com.angelldca.siga.application.port.out.persona;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.Dpersona;

import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaEntity;


public interface PersonaCrudPort extends DeletePort<Dpersona,Long>, GetPort<Dpersona,Long>,
        ListPort<DpersonaEntity>, SavePort<Dpersona> {
}
