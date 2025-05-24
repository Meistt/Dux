package com.challenge.equipos;

import com.challenge.equipos.application.dto.EquipoDTO;
import com.challenge.equipos.application.mapper.EquipoMapper;
import com.challenge.equipos.application.service.EquipoServiceImpl;
import com.challenge.equipos.domain.model.Equipo;
import com.challenge.equipos.domain.model.Liga;
import com.challenge.equipos.domain.model.Pais;
import com.challenge.equipos.domain.repository.EquipoRepository;
import com.challenge.equipos.domain.repository.LigaRepository;
import com.challenge.equipos.domain.repository.PaisRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @Mock
    private LigaRepository ligaRepository;

    @Mock
    private PaisRepository paisRepository;

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

    @Test
    public void crearEquipoTest() {
        // Arrange
        EquipoDTO dto = new EquipoDTO(null, "Boca Juniors", "Primera División", "Argentina");
        Pais argentina = new Pais(1L, "Argentina");
        Liga liga = new Liga(1L, "Primera División", argentina);

        // Mockeo de repositorios
        when(ligaRepository.findByNombreContainingIgnoreCase("Primera División")).thenReturn(liga);
        when(equipoRepository.save(any(Equipo.class))).thenAnswer(invocation -> {
            Equipo eq = invocation.getArgument(0);
            eq.setId(10L); // Simula que la base de datos asigna un ID
            return eq;
        });

        // Act
        EquipoDTO resultado = equipoService.crear(dto);

        // Assert de resultado
        assertNotNull(resultado.getId());
        assertEquals(10L, resultado.getId());
        assertEquals("Boca Juniors", resultado.getNombre());
        assertEquals("Primera División", resultado.getLiga());
        assertEquals("Argentina", resultado.getPais());

        // Verificar que se haya guardado con los valores esperados usando ArgumentCaptor
        ArgumentCaptor<Equipo> captor = ArgumentCaptor.forClass(Equipo.class);
        verify(equipoRepository).save(captor.capture());

        Equipo capturado = captor.getValue();
        assertEquals("Boca Juniors", capturado.getNombre());
        assertEquals("Primera División", capturado.getLiga().getNombre());
        assertEquals("Argentina", capturado.getLiga().getPais().getNombre());
    }


    @Test
    public void modificarEquipoTest() {
        Pais brasil = new Pais(1L, "Brasil");
        Liga ligaBrasilera = new Liga(1L, "Copa Libertadores", brasil);
        Equipo equipoExistente = new Equipo(2L, "Botafogo", ligaBrasilera);

        EquipoDTO equipoDTOmodificado = new EquipoDTO(2L, "Bogotá F.C", "Copa Libertadores", "Brasil");

        when(equipoRepository.findById(2L)).thenReturn(Optional.of(equipoExistente));
        when(equipoRepository.save(any(Equipo.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(ligaRepository.findById(1L)).thenReturn(Optional.of(ligaBrasilera));

        when(equipoMapper.toDTO(any(Equipo.class))).thenAnswer(invocation -> {
            Equipo eq = invocation.getArgument(0);
            return new EquipoDTO(eq.getId(), eq.getNombre(), eq.getLiga().getNombre(), eq.getLiga().getPais().getNombre());
        });

        EquipoDTO resultado = equipoService.modificar(equipoDTOmodificado);

        assertEquals("Bogotá F.C", resultado.getNombre());
        assertEquals("Copa Libertadores", resultado.getLiga()); // no cambia
        assertEquals("Brasil", resultado.getPais());            // no cambia

        verify(equipoRepository).save(any(Equipo.class));
    }


    @Test
    public void eliminarEquipoTest(){
        Equipo equipo = givenTengoUnEquipoDeFutbolQueQuieroBuscar();

        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));

        equipoService.eliminar(equipo.getId());

        verify(equipoRepository).delete(equipo);

    }


    private void whenModificoLosDatosDelEquipo(Equipo equipo) {
        Pais Colombia = new Pais(2L, "Colombia");
        Liga LigaColombiana = new Liga(2L, "Copa Colombia", Colombia);
        equipo.setNombre("Bogotá F.C");
        equipo.setLiga(LigaColombiana);
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
