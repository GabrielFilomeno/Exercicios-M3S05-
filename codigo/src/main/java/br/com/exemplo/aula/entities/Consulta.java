package br.com.exemplo.aula.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "consulta")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // @JoinColumn(name = "id_nutricionista") OPCIONAL ISSO AQUI.
    private Nutricionista nutricionista;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private LocalDate data;
    private String observacoes;

}
