package com.challenge.equipos.infrastructure.repository;

import com.challenge.equipos.domain.model.Equipo;
import com.challenge.equipos.domain.repository.EquipoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaEquipoRepository implements EquipoRepository {

    private final SpringDataEquipoRepository jpaRepository;

    public JpaEquipoRepository(SpringDataEquipoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Equipo> findByNombreContainingIgnoreCase(String nombre) {
        return jpaRepository.findAll();
    }

    @Override
    public Equipo findByNombre(String nombre) {
        return jpaRepository.findByNombre(nombre);
    }

    @Override
    public List<Equipo> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Equipo> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void delete(Equipo equipo) {
        jpaRepository.delete(equipo);
    }

    @Override
    public Equipo save(Equipo equipo) {
        return jpaRepository.save(equipo);
    }
}
