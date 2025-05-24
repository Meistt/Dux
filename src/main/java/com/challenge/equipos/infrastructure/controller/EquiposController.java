package com.challenge.equipos.infrastructure.controller;

import com.challenge.equipos.application.dto.EquipoDTO;
import com.challenge.equipos.domain.interfaces.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/equipos")
public class EquiposController {

    @Autowired
    EquipoService service;

    @Operation(summary = "Listar todos los equipos", description = "Devuelve una lista de todos los equipos registrados en la Base de Datos")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping()
    public ResponseEntity<List<EquipoDTO>> GetEquipos(){
        List<EquipoDTO> list = this.service.getEquipos();

        return list != null ? ResponseEntity.ok().body(list) : ResponseEntity.internalServerError().build();
    }

    @Operation(summary = "Buscar equipo por identificador", description = "Devuelve el equipo asociado al identificador ingresado")
    @ApiResponse(responseCode = "200", description = "Equipo obtenido correctamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @ApiResponse(responseCode = "404", description = "No se encontraron coincidencias")
    @GetMapping("/{id}")
    public ResponseEntity<EquipoDTO> GetEquipoById(@Min(1) @PathVariable("id") Long id){
        EquipoDTO response = this.service.getEquipoById(id);

        return response != null ? ResponseEntity.ok().body(response) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar equipos por nombre", description = "Devuelve una lista de equipos cuyo nombre contenga la cadena indicada")
    @ApiResponse(responseCode = "201", description = "Equipo creado correctamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @ApiResponse(responseCode = "404", description = "No se encontraron coincidencias")
    @GetMapping("/buscar")
    public ResponseEntity<List<EquipoDTO>> GetEquiposByNombre(@NotBlank @Pattern(regexp = "^[a-zA-Z ]+$") @RequestParam("nombre") String nombre){
        List<EquipoDTO> list = this.service.getByNombre(nombre);

        return list != null ? ResponseEntity.ok().body(list) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear equipo", description = "Se da de alta un nuevo equipo asociado a una liga previamente existente")
    @ApiResponse(responseCode = "201", description = "Listado obtenido correctamente o vacío si no hay coincidencias")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @ApiResponse(responseCode = "404", description = "No se encontraron coincidencias")
    @PostMapping()
    public ResponseEntity <EquipoDTO> create(@Valid @RequestBody EquipoDTO equipoDTO) {
        EquipoDTO response = this.service.crear(equipoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Modificar equipo", description = "Se modifican los datos del equipo que previamente debe existir en la base de datos")
    @ApiResponse(responseCode = "200", description = "Equipo modificado con éxito")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @ApiResponse(responseCode = "404", description = "No se encontró el equipo elegido para modificar el equipo")
    @PutMapping("/{id}")
    public ResponseEntity<EquipoDTO> modificar(@Valid @PathVariable() Long id,
                                               @RequestBody EquipoDTO equipoDTO){
        equipoDTO.setId(id);
        EquipoDTO response = this.service.modificar(equipoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Eliminar equipo", description = "Se elimina un equipo previamente ya existente")
    @ApiResponse(responseCode = "204", description = "Equipo eliminado con éxito")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @ApiResponse(responseCode = "404", description = "No se encontró el equipo a eliminar")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@Valid @PathVariable() Long id){
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
