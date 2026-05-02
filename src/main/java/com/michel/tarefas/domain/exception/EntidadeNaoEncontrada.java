package com.michel.tarefas.domain.exception;

public class EntidadeNaoEncontrada extends RuntimeException {
    public EntidadeNaoEncontrada(String message) {
        super(message);
    }
}
