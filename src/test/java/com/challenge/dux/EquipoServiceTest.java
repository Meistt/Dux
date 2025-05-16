package com.challenge.dux;

import com.challenge.dux.application.dto.EquipoDTO;
import com.challenge.dux.application.mapper.EquipoMapper;
import com.challenge.dux.application.service.EquipoServiceImpl;
import com.challenge.dux.domain.model.Equipo;
import com.challenge.dux.domain.model.Liga;
import com.challenge.dux.domain.model.Pais;
import com.challenge.dux.infrastructure.repository.EquipoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void getEquipoByIdTest(){
        Equipo equipo = givenTengoUnEquipoDeFutbolQueQuieroBuscar();
        EquipoDTO dto = new EquipoDTO(1L, "Botafogo", "Copa Libertadores", "Brasil");

        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));
        when(equipoMapper.toDTO(equipo)).thenReturn(dto);

        EquipoDTO resultado = equipoService.getEquipoById(1L);

        assertNotNull(equipo);
        assertEquals(equipo.getNombre(), resultado.getNombre());
    }

    @Test
    public void getEquipoByNombreTest(){
        String nombreBuscado = "boca";

        List<Equipo> equipos = List.of(new Equipo(1L, "Boca Juniors", new Liga(1L, "Liga Profesional", new Pais(1L, "Argentina"))));
        List<EquipoDTO> equipoDTOs = List.of(new EquipoDTO(1L, "Boca Juniors", "Liga Profesional", "Argentina") );

        when(equipoRepository.findByNombreContainingIgnoreCase(nombreBuscado)).thenReturn(equipos);
        when(equipoMapper.toDTOList(equipos)).thenReturn(equipoDTOs);

        List<EquipoDTO> resultado = equipoService.getByNombre(nombreBuscado);

        assertEquals(1, resultado.size());
        assertEquals("Boca Juniors", resultado.get(0).getNombre());
    }

    @Test
    public void getEquiposConMasDeUnaCoincidenciaTest(){
        String nombreBuscado = "madrid";

        List<Equipo> equipos = getEquiposList();
        List<EquipoDTO> equipoDTOs = getEquipoDTOConCoincidenciaList();

        when(equipoRepository.findByNombreContainingIgnoreCase(nombreBuscado)).thenReturn(equipos);
        when(equipoMapper.toDTOList(equipos)).thenReturn(equipoDTOs);

        List<EquipoDTO> resultado = equipoService.getByNombre(nombreBuscado);

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().anyMatch(e -> e.getNombre().equals("Real Madrid")));
        assertTrue(resultado.stream().anyMatch(e -> e.getNombre().equals("Atlético de Madrid")));
    }

    private List<EquipoDTO> getEquipoDTOConCoincidenciaList() {
        return List.of(
                new EquipoDTO(1L, "Real Madrid", "La Liga", "España"),
                new EquipoDTO(2L, "Atlético de Madrid", "La Liga", "España")
        );
    }

    private Equipo givenTengoUnEquipoDeFutbolQueQuieroBuscar() {
        Pais Brasil = new Pais(1L, "Brasil");
        Liga LigaBrasil = new Liga(1L, "Copa Libertadores", Brasil);

        return new Equipo(1L, "Botafogo", LigaBrasil);
    }

    private List<Equipo> getEquiposList() {
        return List.of(
                new Equipo(1L, "Real Madrid", new Liga(1L, "La Liga", new Pais(1L, "España"))),
                new Equipo(2L, "Atlético de Madrid", new Liga(1L, "La Liga", new Pais(1L, "España")))
        );
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
