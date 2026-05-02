package com.michel.tarefas.api.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

    @NotNull
    @Size(max = 100)
    private String nome;

    @Email
    @NotNull
    @Size(max = 100)
    private String email;


}
