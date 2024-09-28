package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.NutricionistaRequestDTO;
import br.com.exemplo.aula.controllers.dto.NutricionistaResponseDTO;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.repositories.NutricionistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NutricionistaServiceTest {

    @Mock
    NutricionistaRepository nutricionistaRepository;

    @InjectMocks
    NutricionistaService nutricionistaService;

    Nutricionista nutricionista;

    @BeforeEach
    public void setup() {
        nutricionista = new Nutricionista(
                1L,
                "teste",
                "teste",
                1,
                "crn123",
                "Especialidade",
                new HashSet<String>(Set.of("Certificação 1", "Certificação 2"))
        );
    }

    @Test
    void listarNutricionistas() {
        List<Nutricionista> nutricionistas = new ArrayList<>();
        nutricionistas.add(nutricionista);

        when(nutricionistaRepository.findAll()).thenReturn((nutricionistas));

        var resultado = nutricionistaService.listarNutricionistas();

        assertNotNull(resultado);
        assertEquals(nutricionistas.get(0).getId(), resultado.get(0).getId());

        verify(nutricionistaRepository).findAll();
    }

    @Test
    void buscarNutricionista() {

        when(nutricionistaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista));

        NutricionistaResponseDTO resultado = nutricionistaService.buscarNutricionista(1L);

        assertNotNull(resultado);
        assertEquals(nutricionista.getId(), resultado.getId());

        verify(nutricionistaRepository).findById(anyLong());
    }

    @Test
    void salvarNutricionista() {

        NutricionistaRequestDTO nutricionistaRequestDTO = new NutricionistaRequestDTO(
                nutricionista.getNome(),
                nutricionista.getMatricula(),
                nutricionista.getTempoExperiencia(),
                1L,
                nutricionista.getCrn(),
                nutricionista.getEspecialidade()
        );

        when(nutricionistaRepository.save(any())).thenReturn(nutricionista);

        var resultado = nutricionistaService.salvarNutricionista(nutricionistaRequestDTO);

        assertNotNull(resultado);
        assertEquals(nutricionistaRequestDTO.getNome(), resultado.getNome());

        verify(nutricionistaRepository).save(any());
    }

    @Test
    void atualizarNutricionista() {
        NutricionistaRequestDTO nutricionistaRequestDTO = new NutricionistaRequestDTO(
                "teste",
                "teste",
                1,
                1L,
                "teste",
                "teste"
        );
        when(nutricionistaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista));
        when(nutricionistaRepository.save(any())).thenReturn(nutricionista);

        var resultado = nutricionistaService.atualizarNutricionista(1L, nutricionistaRequestDTO);

        assertNotNull(resultado);
        assertEquals(nutricionistaRequestDTO.getNome(), resultado.getNome());

        verify(nutricionistaRepository, times(1)).findById(anyLong());
        verify(nutricionistaRepository).save(any());
    }

    @Test
    void removerNutricionista() {
        doNothing().when(nutricionistaRepository).deleteById(anyLong());

        assertDoesNotThrow(
                () -> nutricionistaService.removerNutricionista(1L)
        );

        verify(nutricionistaRepository).deleteById(anyLong());
    }

    @Test
    void adicionarAnoExperiencia() {
        when(nutricionistaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista));

        nutricionistaService.adicionarAnoExperiencia(anyLong());

        assertEquals(1, nutricionista.getTempoExperiencia());

        verify(nutricionistaRepository).findById(anyLong());
    }

    @Test
    void adicionarCertificacao() {
        when(nutricionistaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista));

        nutricionistaService.adicionarCertificacao("Certificação 3", anyLong());

        assertTrue(nutricionista.getCertificacoes().contains("Certificação 3"));

        verify(nutricionistaRepository).findById(anyLong());
    }
}