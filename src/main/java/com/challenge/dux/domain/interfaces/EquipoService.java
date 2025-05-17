package com.challenge.dux.domain.interfaces;

import com.challenge.dux.application.dto.EquipoDTO;

import java.util.List;

public interface EquipoService {
    List<EquipoDTO> getEquipos();
    EquipoDTO getEquipoById(Long id);
    List<EquipoDTO> getByNombre(String nombre);
    EquipoDTO crear(EquipoDTO equipo);
    EquipoDTO modificar(EquipoDTO equipo);
    void eliminar(Long id);
}
