package com.challenge.equipos.domain.interfaces;

import com.challenge.equipos.application.dto.EquipoDTO;

import java.util.List;

public interface EquipoService {
    List<EquipoDTO> getEquipos();
    EquipoDTO getEquipoById(Long id);
    List<EquipoDTO> getByNombre(String nombre);
    EquipoDTO crear(EquipoDTO equipo);
    EquipoDTO modificar(EquipoDTO equipo);
    void eliminar(Long id);
}
