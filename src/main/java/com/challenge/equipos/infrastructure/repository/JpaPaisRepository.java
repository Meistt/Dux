package com.challenge.equipos.infrastructure.repository;

import com.challenge.equipos.domain.model.Pais;
import com.challenge.equipos.domain.repository.PaisRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaPaisRepository implements PaisRepository {

    private final SpringDataPaisRepository paisRepository;

    public JpaPaisRepository(SpringDataPaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @Override
    public Pais findByNombreContainingIgnoreCase(String nombre) {
        return paisRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Optional<Pais> findById(Long id) {
        return paisRepository.findById(id);
    }
}
