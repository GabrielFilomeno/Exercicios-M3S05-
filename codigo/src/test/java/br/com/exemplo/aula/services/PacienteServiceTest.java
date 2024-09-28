package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.PacienteRequestDTO;
import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.entities.Paciente;
import br.com.exemplo.aula.mapper.PacienteMapper;
import br.com.exemplo.aula.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


// EX 02
@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    PacienteRepository pacienteRepository;

    @InjectMocks
    PacienteService pacienteService;

    @Spy
    PacienteMapper pacienteMapper = Mappers.getMapper(PacienteMapper.class);

    Paciente paciente;

    @BeforeEach
    public void setup() {
        paciente = new Paciente(
                1L,
                "teste",
                LocalDate.now(),
                "111.111.111-11",
                "99999999",
                "teste@email"
        );
    }



    @Test
    void listarPacientes() {

        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(paciente);

        when(pacienteRepository.findAll()).thenReturn(pacientes);

        var resultado = pacienteService.listarPacientes();

        assertNotNull(resultado);
        assertEquals(pacientes.get(0).getId(), resultado.get(0).getId());

        verify(pacienteRepository).findAll();
    }

    @Test
    void buscarPaciente() {

        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(paciente));

        PacienteResponseDTO resultado = pacienteService.buscarPaciente(1L);

        assertNotNull(resultado);
        assertEquals(paciente.getId(), resultado.getId());

        verify(pacienteRepository).findById(anyLong());
    }

    @Test
    void salvarPaciente() {

        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO(
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail(),
                1L
        );

        when(pacienteRepository.save(any())).thenReturn(paciente);

        var resultado = pacienteService.salvarPaciente(pacienteRequestDTO);

        assertNotNull(resultado);
        assertEquals(pacienteRequestDTO.getNome(), resultado.getNome());

        verify(pacienteRepository).save(any());
    }

    @Test
    void atualizarPaciente() {
        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO(
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail(),
                1L
        );

        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        var resultado = pacienteService.atualizarPaciente(1L, pacienteRequestDTO);

        assertNotNull(resultado);
        assertEquals(pacienteRequestDTO.getNome(), resultado.getNome());

        verify(pacienteRepository, times(1)).findById(anyLong());
        verify(pacienteRepository).save(any());
    }

    @Test
    void removerPaciente() {
        doNothing().when(pacienteRepository).deleteById(anyLong());

        assertDoesNotThrow(
                () -> pacienteService.removerPaciente(1L)
        );

        verify(pacienteRepository).deleteById(anyLong());
    }
}