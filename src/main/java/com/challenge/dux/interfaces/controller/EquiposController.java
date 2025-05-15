package com.challenge.dux.interfaces.controller;

import com.challenge.dux.application.dto.EquipoDTO;
import com.challenge.dux.domain.interfaces.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/api/equipos")
public class EquiposController {

    @Autowired
    EquipoService service;

    @GetMapping()
    public ResponseEntity<List<EquipoDTO>> GetEquipos(){
        List<EquipoDTO> list = this.service.getEquipos();

        return list != null ? ResponseEntity.ok().body(list) : ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoDTO> GetEquipoById(@PathVariable("id") Long id){
        EquipoDTO response = this.service.getEquipoById(id);

        return response != null ? ResponseEntity.ok().body(response) : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EquipoDTO>> GetEquiposByNombre(@RequestParam("nombre") String nombre){
        List<EquipoDTO> list = this.service.getByNombre(nombre);

        return list != null ? ResponseEntity.ok().body(list) : ResponseEntity.notFound().build();
    }
}
