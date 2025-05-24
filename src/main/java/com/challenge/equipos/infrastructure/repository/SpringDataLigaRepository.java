package com.challenge.equipos.infrastructure.repository;

import com.challenge.equipos.domain.model.Liga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataLigaRepository extends JpaRepository<Liga, Long> {
    Liga findByNombreContainingIgnoreCase(String nombre);
}
