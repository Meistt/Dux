package com.challenge.dux.domain.interfaces;

import com.challenge.dux.application.dto.EquipoDTO;

import java.util.List;

public interface EquipoService {
    public List<EquipoDTO> getEquipos();
    public EquipoDTO getEquipoById(Long id);
}
