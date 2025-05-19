package com.challenge.dux.application.service;

import com.challenge.dux.application.dto.EquipoDTO;
import com.challenge.dux.application.exceptions.RecursoNoEncontradoException;
import com.challenge.dux.application.mapper.EquipoMapper;
import com.challenge.dux.domain.interfaces.EquipoService;
import com.challenge.dux.domain.model.Equipo;
import com.challenge.dux.domain.model.Liga;
import com.challenge.dux.domain.model.Pais;
import com.challenge.dux.infrastructure.repository.EquipoRepository;
import com.challenge.dux.infrastructure.repository.LigaRepository;
import com.challenge.dux.infrastructure.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    EquipoRepository repository;

    @Autowired
    LigaRepository ligaRepository;

    @Autowired
    PaisRepository paisRepository;

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
            Equipo equipo = this.repository.findById(id)
                    .orElseThrow(() -> new RecursoNoEncontradoException("Equipo no encontrado"));
            return mapper.toDTO(equipo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EquipoDTO> getByNombre(String nombre) {
        try {
            List<Equipo> list = this.repository.findByNombreContainingIgnoreCase(nombre);
            return mapper.toDTOList(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EquipoDTO crear(EquipoDTO dto) {
        Liga liga = ligaRepository.findByNombreContainingIgnoreCase(dto.getLiga());

        if (liga == null) {
            throw new RecursoNoEncontradoException("Liga no encontrada: " + dto.getLiga());
        }

        Equipo equipo = new Equipo();
        equipo.setNombre(dto.getNombre());
        equipo.setLiga(liga);

        Equipo equipoGuardado = repository.save(equipo);

        return new EquipoDTO(equipoGuardado.getId(), equipoGuardado.getNombre(), liga.getNombre(), liga.getPais().getNombre());
    }

    @Override
    public EquipoDTO modificar(EquipoDTO equipo) {
        Equipo aux = repository.findById(equipo.getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Equipo no encontrado"));

        Pais pais = getPais(equipo.getPais());
        Liga liga = getLiga(equipo.getLiga(), pais);
        setEquipoInfo(aux, equipo.getNombre(), liga);
        this.repository.save(aux);
        return mapper.toDTO(aux);
    }

    @Override
    public void eliminar(Long id) {
        Equipo equipo = repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Equipo no encontrado"));
        repository.delete(equipo);
    }

    private Equipo setEquipoInfo(Equipo aux, String nombre, Liga liga) {
        aux.setNombre(nombre);
        aux.setLiga(liga);
        return aux;
    }

    public Pais getPais(String nombre) {
        Pais pais = this.paisRepository.findByNombreContainingIgnoreCase(nombre);
        if (pais == null) {
            pais = new Pais();
            pais.setNombre(nombre);
            pais = paisRepository.save(pais);
        }
        return pais;
    }

    public Liga getLiga(String liga, Pais pais) {
        Liga aux = ligaRepository.findByNombreContainingIgnoreCase(liga);
        if (aux == null) {
            aux = new Liga();
            aux.setNombre(liga);
            aux.setPais(pais);
            aux = ligaRepository.save(aux);
        }
        return aux;
    }
}
