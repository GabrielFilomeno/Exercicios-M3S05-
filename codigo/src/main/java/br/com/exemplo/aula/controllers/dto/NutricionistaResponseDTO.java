package br.com.exemplo.aula.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NutricionistaResponseDTO {

    private Long id;
    private String nome;
    private String matricula;
    private int tempoExperiencia;
    private String crn;
    private String especialidade;
}