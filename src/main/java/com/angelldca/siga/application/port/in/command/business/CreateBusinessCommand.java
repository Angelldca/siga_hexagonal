package com.angelldca.siga.application.port.in.command.business;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBusinessCommand {
    private String nombre;
    private String logo;
    private String address;
}
