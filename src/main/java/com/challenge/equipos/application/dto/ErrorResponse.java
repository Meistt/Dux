package com.challenge.equipos.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ErrorResponse {
    private String mensaje;
    private int codigo;
    List<String> errores;

    public ErrorResponse(String mensaje, int codigo) {
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

    public ErrorResponse(String mensaje, int codigo, List<String> errores) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.errores = errores;
    }
}
