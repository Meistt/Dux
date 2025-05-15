package com.challenge.dux.application.service;

import com.challenge.dux.application.dto.EquipoDTO;
import com.challenge.dux.application.mapper.EquipoMapper;
import com.challenge.dux.domain.interfaces.EquipoService;
import com.challenge.dux.domain.model.Equipo;
import com.challenge.dux.infrastructure.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    EquipoRepository repository;

    @Autowired
    EquipoMapper mapper;

    private List<EquipoDTO> list;


    @Override
    public List<EquipoDTO> getEquipos() {
        List<Equipo> equipos;
        try{
            equipos = repository.findAll();
            list = mapper.toDTOList(equipos);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public EquipoDTO getEquipoById(Long id) {
        try {
            Equipo equipo = this.repository.findById(id).get();
            return mapper.toDTO(equipo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
