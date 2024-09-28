package br.com.exemplo.aula.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class PacienteRequestDTO {

    private String nome;

    @JsonDeserialize
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String cpf;
    private String telefone;
    private String email;
    private Long idEndereco;

}
