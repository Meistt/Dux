package com.challenge.dux.infrastructure.repository;

import com.challenge.dux.domain.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
    Equipo findByNombre(String nombre);
}
