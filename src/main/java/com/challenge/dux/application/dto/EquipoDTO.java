package com.challenge.dux.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EquipoDTO {
    private Long id;
    private String nombre;
    private String liga;
    private String pais;

    public EquipoDTO(Long id, String nombre, String liga, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.liga = liga;
        this.pais = pais;
    }
}
