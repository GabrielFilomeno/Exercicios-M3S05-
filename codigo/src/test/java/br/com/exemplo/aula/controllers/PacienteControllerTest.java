package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.services.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// EX 05
@WebMvcTest(controllers = PacienteController.class)
@AutoConfigureMockMvc
@ActiveProfiles("Test")
class PacienteControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PacienteService pacienteService;

    @Test
    void listarPacientes() throws Exception {

        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO(
                1L,
                "teste",
                LocalDate.now(),
                "CPF",
                "1111111",
                "mail@main"
        );

        when(pacienteService.listarPacientes()).thenReturn(List.of(pacienteResponseDTO));

        mvc.perform(get("/pacientes"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$[0].nome").value(pacienteResponseDTO.getNome())
                )
        ;

        verify(pacienteService).listarPacientes();
    }

    @Test
    void adicionarPaciente() throws Exception {

        when(pacienteService.salvarPaciente(any())).thenReturn(new PacienteResponseDTO(
                1L,
                "teste",
                LocalDate.of(2000, 10, 10),
                "111.111.111-11",
                "(99) 99999-9999",
                "email@email.com"));

        mvc.perform(post("/pacientes")
                        .header("Authorization","token fake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                             {
                                 "nome": "teste",
                                 "dataNascimento": "10/10/1010",
                                 "cpf": "teste",
                                 "telefone": "teste",
                                 "email": "teste",
                                 "idEndereco": 1
                             }"""))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("teste"));

        verify(pacienteService).salvarPaciente(any());
    }

    @Test
    void search() throws Exception {

        when(pacienteService.buscarPaciente(anyLong())).thenReturn(new PacienteResponseDTO(
                1L,
                "teste",
                LocalDate.of(2000, 10, 10),
                "111.111.111-11",
                "(99) 99999-9999",
                "email@email.com"));

        mvc.perform(get("/pacientes/1")
                .header("Authorization","token fake")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("teste"));
    }

    @Test
    void remove() throws Exception {
        mvc.perform(delete("/pacientes/1"))
                .andExpect(status().isNoContent());
        verify(pacienteService).removerPaciente(any());
    }

    @Test
    void update() throws Exception {
        when(pacienteService.atualizarPaciente(anyLong(), any())).thenReturn(new PacienteResponseDTO(
                1L,
                "teste atualizado",
                LocalDate.of(2000, 10, 10),
                "111.111.111-11",
                "(99) 99999-9999",
                "email@email.com"));

        mvc.perform(put("/pacientes/1")
                        .header("Authorization","token fake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                             {
                                 "nome": "teste atualizado",
                                 "dataNascimento": "10/10/1010",
                                 "cpf": "teste",
                                 "telefone": "teste",
                                 "email": "teste",
                                 "idEndereco": 1
                             }"""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("teste atualizado"));

        verify(pacienteService).atualizarPaciente(anyLong(), any());
    }
}