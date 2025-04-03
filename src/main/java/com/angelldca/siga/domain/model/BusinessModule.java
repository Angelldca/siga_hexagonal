package com.angelldca.siga.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessModule {
    private UUID id;
    private Empresa business;
    private Module module;
}
