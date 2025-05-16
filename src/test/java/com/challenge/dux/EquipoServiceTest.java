package com.challenge.dux;

import com.challenge.dux.application.dto.EquipoDTO;
import com.challenge.dux.application.mapper.EquipoMapper;
import com.challenge.dux.application.service.EquipoServiceImpl;
import com.challenge.dux.domain.model.Equipo;
import com.challenge.dux.domain.model.Liga;
import com.challenge.dux.domain.model.Pais;
import com.challenge.dux.infrastructure.repository.EquipoRepository;
import com.challenge.dux.infrastructure.repository.LigaRepository;
import com.challenge.dux.infrastructure.repository.PaisRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @Mock
    private EquipoMapper equipoMapper;

    @InjectMocks
    private EquipoServiceImpl equipoService;

    @Test
    public void testGetEquipos() {
        List<Equipo> equipos = givenTengoUnaListaDeEquiposEnMiBaseDeDatos();
        List<EquipoDTO> dto = getEquipoDTOList();

        when(equipoRepository.findAll()).thenReturn(equipos);
        when(equipoMapper.toDTOList(equipos)).thenReturn(dto);

        List<EquipoDTO> resultado = equipoService.getEquipos();

        assertEquals(4, resultado.size());
        assertNotNull(resultado);
        assertEquals("FC Barcelona", resultado.get(2).getNombre());
    }

    private List<EquipoDTO> getEquipoDTOList() {
        List<EquipoDTO> dtoList = new ArrayList<>();
        dtoList.add(new EquipoDTO(1L, "Chaco Forever", "Nacional B", "Argentina"));
        dtoList.add(new EquipoDTO(2L, "Botafogo", "Copa Libertadores", "Brasil"));
        dtoList.add(new EquipoDTO(3L, "FC Barcelona", "La Liga", "España"));
        dtoList.add(new EquipoDTO(4L, "AC Milan", "Serie A", "Italia"));

        return dtoList;
    }

    private List<Equipo> givenTengoUnaListaDeEquiposEnMiBaseDeDatos() {
        List<Equipo> equipos = new ArrayList<>();

        Pais Argentina = new Pais(1L, "Argentina");
        Pais Brasil = new Pais(2L, "Brasil");
        Pais España = new Pais(3L, "España");
        Pais Italia = new Pais(4L, "Italia");

        Liga LigaArgentina = new Liga(1L, "Nacional B", Argentina);
        Liga LigaBrasil = new Liga(2L, "Copa Libertadores", Brasil);
        Liga LigaEspaña = new Liga(3L, "La Liga", España);
        Liga LigaItalia = new Liga(4L, "Serie A", Italia);

        Equipo ChacoForever = new Equipo(1L, "Chaco Forever", LigaArgentina);
        Equipo Botafogo = new Equipo(2L, "Botafogo", LigaBrasil);
        Equipo Barcelona = new Equipo(3L, "FC Barcelonar", LigaEspaña);
        Equipo Milan = new Equipo(4L, "AC Milan", LigaItalia);

        equipos.add(ChacoForever);
        equipos.add(Botafogo);
        equipos.add(Barcelona);
        equipos.add(Milan);

        return equipos;
    }
}
