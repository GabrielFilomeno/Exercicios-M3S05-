package br.com.exemplo.aula.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "paciente")
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long id;

    @Column(nullable = false)
    private String nome;

    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String cpf;

    private String telefone;
    private String email;


}


