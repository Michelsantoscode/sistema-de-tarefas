package com.michel.tarefas.domain.service;

import com.michel.tarefas.domain.exception.EntidadeNaoEncontrada;
import com.michel.tarefas.domain.exception.NegocioException;
import com.michel.tarefas.domain.model.Status;
import com.michel.tarefas.domain.model.Tarefa;
import com.michel.tarefas.domain.model.Usuario;
import com.michel.tarefas.domain.repository.TarefaRepository;
import com.michel.tarefas.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Tarefa adicionar(Tarefa tarefa) {

        Usuario usuario = usuarioRepository.findById(tarefa.getUsuario().getId())
                .orElseThrow(() -> new EntidadeNaoEncontrada("Usuário não encontrado"));

        tarefa.setUsuario(usuario);

        if (tarefa.getStatus() == null) {
            tarefa.setStatus(Status.PENDENTE);
        }

        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public void excluir (Long tarefaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                        .orElseThrow(() -> new EntidadeNaoEncontrada("Tarefa nao encontrada"));

        tarefaRepository.delete(tarefa);
    }

    @Transactional
    public void status (Long tarefaId, Status status) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new EntidadeNaoEncontrada("Tarefa nao encontrada"));

        tarefa.setStatus(status);

        if(status == Status.CONCLUIDA) {
            tarefa.setDataConclusao(LocalDate.now());
        } else {
            tarefa.setDataConclusao(null);
        }
        tarefaRepository.save(tarefa);

    }

    public List<Tarefa> listar() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscar (Long tarefaId) {
         return tarefaRepository.findById(tarefaId)
                 .orElseThrow(() -> new EntidadeNaoEncontrada("Tarefa nao encontrada"));
    }

    @Transactional
    public Tarefa atualizar (Long tarefaId, Tarefa tarefa) {
        Tarefa tarefaAtual = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new NegocioException("Tarefa nao encontrado"));


        tarefaAtual.setTitulo(tarefa.getTitulo());
        tarefaAtual.setDescricao(tarefa.getDescricao());

        return tarefaRepository.save(tarefaAtual);

    }}
