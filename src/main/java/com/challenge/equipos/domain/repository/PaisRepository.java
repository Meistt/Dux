package com.challenge.equipos.domain.repository;

import com.challenge.equipos.domain.model.Pais;

import java.util.Optional;

public interface PaisRepository{
    Pais findByNombreContainingIgnoreCase(String nombre);
    Optional<Pais> findById(Long id);
}
