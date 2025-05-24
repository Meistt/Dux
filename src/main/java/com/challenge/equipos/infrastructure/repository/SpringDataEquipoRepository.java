package com.challenge.equipos.infrastructure.repository;

import com.challenge.equipos.domain.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEquipoRepository extends JpaRepository<Equipo, Long> {
    Equipo findByNombre(String nombre);
}
