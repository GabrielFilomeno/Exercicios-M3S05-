package br.com.exemplo.aula.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "nutricionista")
@AllArgsConstructor
@NoArgsConstructor
public class Nutricionista {

    public Nutricionista(Long id, String nome, String matricula, int tempoExperiencia, String crn, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.tempoExperiencia = tempoExperiencia;
        this.crn = crn;
        this.especialidade = especialidade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nutricionista")
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String matricula;
    private int tempoExperiencia;

    @Column(nullable = false)
    private String crn;

    private String especialidade;

    @ElementCollection
    private Set<String> certificacoes = new HashSet<>();


    public Nutricionista(long l, String teste, String teste1, int i, String crn123, String teste2, Object o, String especialidade, HashSet<String> strings) {
    }

    public Nutricionista(long id, String nome, String matricula, int tempoExperiencia, String crn, String especialidade, HashSet<String> strings) {
    }
}
