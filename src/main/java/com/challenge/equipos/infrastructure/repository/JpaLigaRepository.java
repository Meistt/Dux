package com.challenge.equipos.infrastructure.repository;

import com.challenge.equipos.domain.model.Liga;
import com.challenge.equipos.domain.repository.LigaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaLigaRepository implements LigaRepository {

    private final SpringDataLigaRepository ligaRepository;

    public JpaLigaRepository(SpringDataLigaRepository ligaRepository) {
        this.ligaRepository = ligaRepository;
    }

    @Override
    public Liga findByNombreContainingIgnoreCase(String nombre) {
        return ligaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Optional<Liga> findById(Long id) {
        return ligaRepository.findById(id);
    }
}
