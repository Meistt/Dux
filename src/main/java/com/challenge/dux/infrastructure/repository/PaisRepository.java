package com.challenge.dux.infrastructure.repository;

import com.challenge.dux.domain.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    Pais findByNombreContainingIgnoreCase(String nombre);
}
