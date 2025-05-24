package com.challenge.equipos.application.mapper;

import com.challenge.equipos.application.dto.EquipoDTO;
import com.challenge.equipos.domain.model.Equipo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipoMapper {

    public EquipoDTO toDTO(Equipo equipo) {
        return new EquipoDTO(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getLiga().getNombre(),
                equipo.getLiga().getPais().getNombre()
        );
    }

    public List<EquipoDTO> toDTOList(List<Equipo> equipos) {
        return equipos.stream()
                .map(this::toDTO)
                .toList();
    }
}
