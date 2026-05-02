package com.michel.tarefas.api.assembler;

import com.michel.tarefas.api.model.UsuarioModel;
import com.michel.tarefas.api.model.input.UsuarioInput;
import com.michel.tarefas.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UsuarioAssembler {

    private ModelMapper modelMapper;

    public Usuario toEntity (UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public UsuarioModel toModel (Usuario usuario) {
        return modelMapper.map(usuario, UsuarioModel.class);
    }

    public List<UsuarioModel> toCollection (List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toModel)
                .toList();
    }

}
