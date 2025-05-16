package com.challenge.dux.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipoDTO {
    private Long id;
    private String nombre;
    private String liga;
    private String pais;

}
