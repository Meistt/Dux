package com.challenge.dux.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EquiposController {

    @Autowired
    EquipoService service;

    @GetMapping("/equipos")
    public String GetEquipos(){
        return "Hola";
    }
}
