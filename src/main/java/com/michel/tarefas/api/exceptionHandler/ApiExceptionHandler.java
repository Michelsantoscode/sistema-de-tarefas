package com.michel.tarefas.api.exceptionHandler;

import com.michel.tarefas.domain.exception.EntidadeNaoEncontrada;
import com.michel.tarefas.domain.exception.NegocioException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> capturar (NegocioException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(400);
        problemDetail.setTitle("erro de negocio");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create(("/erros/negocio")));

        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler
    public ResponseEntity<ProblemDetail> entidadeNaoEncontrada (EntidadeNaoEncontrada e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(404);
            problemDetail.setTitle("Entidade nao encontrada");
            problemDetail.setDetail(e.getMessage());
            problemDetail.setType(URI.create("/erros/entidade"));

            return ResponseEntity.status(404).body(problemDetail);
        }
    }

