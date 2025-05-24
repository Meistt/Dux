package com.challenge.equipos.domain.repository;

import com.challenge.equipos.domain.model.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository{
    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
    Equipo findByNombre(String nombre);
    List<Equipo> findAll();
    Optional<Equipo> findById(Long id);
    void delete(Equipo equipo);
    Equipo save(Equipo equipo);
}
