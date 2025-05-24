package com.challenge.equipos.application.service;

import com.challenge.equipos.domain.repository.EquipoRepository;
import com.challenge.equipos.domain.repository.LigaRepository;
import com.challenge.equipos.domain.repository.PaisRepository;
import com.challenge.equipos.application.dto.EquipoDTO;
import com.challenge.equipos.domain.interfaces.EquipoService;
import com.challenge.equipos.domain.model.Equipo;
import com.challenge.equipos.domain.model.Liga;
import com.challenge.equipos.domain.model.Pais;
import com.challenge.equipos.application.exceptions.RecursoNoEncontradoException;
import com.challenge.equipos.application.mapper.EquipoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository repository;
    private final LigaRepository ligaRepository;
    private final PaisRepository paisRepository;

    private final EquipoMapper mapper;

    public EquipoServiceImpl(EquipoRepository repository,
                             LigaRepository ligaRepository,
                             PaisRepository paisRepository,
                             EquipoMapper mapper) {
        this.repository = repository;
        this.ligaRepository = ligaRepository;
        this.paisRepository = paisRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EquipoDTO> getEquipos() {
        List<Equipo> equipos = repository.findAll();
        return mapper.toDTOList(equipos);
    }

    @Override
    public EquipoDTO getEquipoById(Long id) {
        Equipo equipo = repository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("Equipo con ID " + id + " no encontrado"));
        return mapper.toDTO(equipo);
    }

    @Override
    public List<EquipoDTO> getByNombre(String nombre) {
        List<Equipo> equipos = repository.findByNombreContainingIgnoreCase(nombre);
        return mapper.toDTOList(equipos);
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

        Equipo guardado = repository.save(equipo);

        return new EquipoDTO(guardado.getId(), guardado.getNombre(), liga.getNombre(), liga.getPais().getNombre());
    }

    @Override
    public EquipoDTO modificar(EquipoDTO equipo) {
        Equipo equipoExistente = repository.findById(equipo.getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Equipo no encontrado con ID " + equipo.getId()));

        Liga liga = getLiga(equipoExistente.getLiga());

        equipoExistente.setNombre(equipo.getNombre());
        equipoExistente.setLiga(liga);

        repository.save(equipoExistente);
        return mapper.toDTO(equipoExistente);
    }

    @Override
    public void eliminar(Long id) {
        Equipo equipo = repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Equipo no encontrado con ID " + id));
        repository.delete(equipo);
    }

    public Pais getPais(Long id) {
        return this.paisRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("Equipo con ID " + id + " no encontrado"));
    }

    public Liga getLiga(Liga liga) {
        return ligaRepository.findById(liga.getId())
                .orElseThrow(()-> new RecursoNoEncontradoException("Liga " + liga.getId() + " no encontrada"));
    }
}
