package br.com.exemplo.aula.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class ConsultaRequestDTO {

    private Long idNutricionista;
    private Long idPaciente;

    @JsonDeserialize
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private String observacoes;
}
