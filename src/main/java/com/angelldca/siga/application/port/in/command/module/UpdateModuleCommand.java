package com.angelldca.siga.application.port.in.command.module;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModuleCommand {
    private String name;
    private String image;
    private String description;
}
