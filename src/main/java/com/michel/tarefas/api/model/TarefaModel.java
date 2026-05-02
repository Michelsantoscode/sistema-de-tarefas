package com.michel.tarefas.api.model;

import com.michel.tarefas.domain.model.Status;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class TarefaModel {

    private Long id;
    private String titulo;
    private String descricao;
    private Status status;

    private LocalDate dataCadastro;

    private LocalDate dataConclusao;

    private UsuarioModel usuario;

}
