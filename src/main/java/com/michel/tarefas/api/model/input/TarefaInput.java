package com.michel.tarefas.api.model.input;

import com.michel.tarefas.domain.model.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarefaInput {

    @NotNull
    @Size(max = 20)
    private String titulo;

    @NotNull
    @Size(max = 40)
    private String descricao;

    private Status status;

    private Long usuarioId;

}
