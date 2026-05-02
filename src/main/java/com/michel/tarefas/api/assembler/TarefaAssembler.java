package com.michel.tarefas.api.assembler;

import com.michel.tarefas.api.model.TarefaModel;
import com.michel.tarefas.api.model.input.TarefaInput;
import com.michel.tarefas.domain.model.Tarefa;
import com.michel.tarefas.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TarefaAssembler {

    private final ModelMapper modelMapper;


    public Tarefa toEntity(TarefaInput input) {
        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(input.getTitulo());
        tarefa.setDescricao(input.getDescricao());
        tarefa.setStatus(input.getStatus());

        if (input.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(input.getUsuarioId());
            tarefa.setUsuario(usuario);
        }

        return tarefa;
    }

    public TarefaModel toModel (Tarefa tarefa) {
        return modelMapper.map(tarefa, TarefaModel.class);
    }

    public List<TarefaModel> toCollection (List<Tarefa> tarefas) {
        return tarefas.stream()
                .map(this::toModel)
                .toList();
    }

}
