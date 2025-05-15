package com.challenge.dux.infrastructure.repository;

import com.challenge.dux.domain.model.Liga;
import org.springframework.data.repository.CrudRepository;

public interface LigaRepository extends CrudRepository<Liga, Long> {
    Liga findByNombreContainingIgnoreCase(String nombre);
}
