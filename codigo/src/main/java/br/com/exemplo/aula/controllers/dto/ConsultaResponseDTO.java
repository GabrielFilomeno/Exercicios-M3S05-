package br.com.exemplo.aula.controllers.dto;

import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultaResponseDTO {

    private Long id;
    private Nutricionista nutricionista;
    private Paciente paciente;

    @JsonSerialize
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private String observacoes;
}
