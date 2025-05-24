package com.challenge.equipos.domain.repository;

import com.challenge.equipos.domain.model.Liga;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LigaRepository{
    Liga findByNombreContainingIgnoreCase(String nombre);
    Optional<Liga> findById(Long id);
}
