package com.michel.tarefas.api.controller;

import com.michel.tarefas.api.assembler.TarefaAssembler;
import com.michel.tarefas.api.model.TarefaModel;
import com.michel.tarefas.api.model.UsuarioModel;
import com.michel.tarefas.api.model.input.StatusInput;
import com.michel.tarefas.api.model.input.TarefaInput;
import com.michel.tarefas.api.model.input.UsuarioInput;
import com.michel.tarefas.domain.model.Status;
import com.michel.tarefas.domain.model.Tarefa;
import com.michel.tarefas.domain.model.Usuario;
import com.michel.tarefas.domain.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;
    private final TarefaAssembler tarefaAssembler;

    @Operation(summary = "Adicionar Tarefa por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operacao realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invaldia"),
    })
    @PostMapping()
    public ResponseEntity<TarefaModel> adicionar (@Valid @RequestBody TarefaInput tarefaInput) {
        Tarefa tarefa = tarefaAssembler.toEntity(tarefaInput);
        Tarefa salvo = tarefaService.adicionar(tarefa);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tarefaAssembler.toModel(salvo));

    }

    @Operation(summary = "Excluir Tarefa por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operacao realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @DeleteMapping("/{tarefaId}")
    public ResponseEntity<Void> excluir (@PathVariable Long tarefaId) {
        tarefaService.excluir(tarefaId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar Tarefas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacao realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
    })
    @GetMapping
    public List<TarefaModel> listar () {
       return tarefaAssembler.toCollection(tarefaService.listar());
    }

    @Operation(summary = "Alterar status da tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Status atualizado"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarStatus(@PathVariable Long id, @RequestBody StatusInput input) {
        tarefaService.status(id, input.getStatus());
    }

    @Operation(summary = "Buscar tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Status atualizado"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/{tarefaId}")
    public ResponseEntity<TarefaModel> buscar (@PathVariable Long tarefaId) {
        Tarefa tarefa = tarefaService.buscar(tarefaId);
        return ResponseEntity.ok(tarefaAssembler.toModel(tarefa));
    }

    @Operation(summary = "Atualizar Tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacao realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Tarefa nao encontrada"),
    })
    @PutMapping("/{tarefaId}")
    public ResponseEntity<TarefaModel> atualizar (@PathVariable Long tarefaId,
                                                   @RequestBody @Valid TarefaInput tarefaInput) {
        Tarefa tarefa = tarefaAssembler.toEntity(tarefaInput);
        Tarefa tarefaAtualizada = tarefaService.atualizar(tarefaId, tarefa);

        return ResponseEntity.ok(tarefaAssembler.toModel(tarefaAtualizada));

    }

}
