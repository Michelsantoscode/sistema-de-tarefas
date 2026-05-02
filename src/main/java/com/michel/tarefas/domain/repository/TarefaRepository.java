package com.michel.tarefas.domain.repository;

import com.michel.tarefas.domain.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {



}
