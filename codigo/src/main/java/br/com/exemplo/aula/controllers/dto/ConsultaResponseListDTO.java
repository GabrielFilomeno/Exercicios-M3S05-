package br.com.exemplo.aula.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultaResponseListDTO {

    private Long id;
    private String nomeNutricionista;
    private String nomePaciente;
    private LocalDate data;
}
