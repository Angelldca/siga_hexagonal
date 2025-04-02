package com.angelldca.siga.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {
    private Long id;
    private String name;
    private String image;
    private String description;
    private List<Permission> permissions;
}
