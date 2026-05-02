package com.michel.tarefas.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Tarefa {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String titulo;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


}
