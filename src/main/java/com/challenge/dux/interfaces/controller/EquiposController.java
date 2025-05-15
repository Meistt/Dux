package com.challenge.dux.interfaces.controller;

import com.challenge.dux.application.dto.EquipoDTO;
import com.challenge.dux.domain.interfaces.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/api")
public class EquiposController {

    @Autowired
    EquipoService service;

    @GetMapping("/equipos")
    public ResponseEntity<List<EquipoDTO>> GetEquipos(){
        List<EquipoDTO> list = this.service.getEquipos();

        return list != null ? ResponseEntity.ok().body(list) : ResponseEntity.internalServerError().build();
    }
}
