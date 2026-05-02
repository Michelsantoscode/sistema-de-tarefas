package com.michel.tarefas.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @OneToMany(mappedBy = "usuario")
    private List<Tarefa> tarefas;

}
