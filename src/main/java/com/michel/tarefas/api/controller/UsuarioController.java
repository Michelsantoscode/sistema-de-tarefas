package com.michel.tarefas.api.controller;

import com.michel.tarefas.api.assembler.UsuarioAssembler;
import com.michel.tarefas.api.model.UsuarioModel;
import com.michel.tarefas.api.model.input.UsuarioInput;
import com.michel.tarefas.domain.model.Usuario;
import com.michel.tarefas.domain.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioAssembler usuarioAssembler;


    @Operation(summary = "Adicionar Usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operacao realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invaldia"),
    })
    @PostMapping()
    public ResponseEntity <UsuarioModel> adicionar (@Valid @RequestBody UsuarioInput usuarioInput) {
        Usuario usuario = usuarioAssembler.toEntity(usuarioInput);
        Usuario novoUsuario = usuarioService.adicionar(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioAssembler.toModel(novoUsuario));
    }

    @Operation(summary = "Listar Usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacao realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invaldia"),
    })
    @GetMapping()
    public List<UsuarioModel> listar () {
         return usuarioAssembler.toCollection(usuarioService.listar());
    }

    @Operation(summary = "Excluir Usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operacao realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invaldia"),
    })
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity <Void> excluir(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar Usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacao realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuario nao encontrado"),
    })
    @GetMapping("/{usuarioId}")
    public ResponseEntity <UsuarioModel> buscar (@PathVariable Long usuarioId) {
       Usuario usuario = usuarioService.buscar(usuarioId);
       return ResponseEntity.ok(usuarioAssembler.toModel(usuario));
    }


    @Operation(summary = "Atualizar Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacao realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuario nao encontrado"),
    })
    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioModel> atualizar (@PathVariable Long usuarioId,
                                                   @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuario = usuarioAssembler.toEntity(usuarioInput);
        Usuario usuarioAtualizado = usuarioService.atualizar(usuarioId, usuario);

        return ResponseEntity.ok(usuarioAssembler.toModel(usuarioAtualizado));

    }


}
