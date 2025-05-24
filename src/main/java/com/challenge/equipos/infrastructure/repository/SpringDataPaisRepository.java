package com.challenge.equipos.infrastructure.repository;

import com.challenge.equipos.domain.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPaisRepository extends JpaRepository<Pais, Long> {
    Pais findByNombreContainingIgnoreCase(String nombre);
}
