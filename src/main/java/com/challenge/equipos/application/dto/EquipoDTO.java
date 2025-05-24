package com.challenge.equipos.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipoDTO {
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String liga;

    @NotBlank
    private String pais;

}
