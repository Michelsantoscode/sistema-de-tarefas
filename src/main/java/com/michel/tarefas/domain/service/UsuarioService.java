package com.michel.tarefas.domain.service;

import com.michel.tarefas.domain.exception.EntidadeNaoEncontrada;
import com.michel.tarefas.domain.exception.NegocioException;
import com.michel.tarefas.domain.model.Usuario;
import com.michel.tarefas.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario adicionar (Usuario usuario) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new NegocioException("Email ja cadastrado");
        }

        return usuarioRepository.save(usuario);
    }

    public List <Usuario> listar () {
      return usuarioRepository.findAll();
    }

    @Transactional
    public void excluir (Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntidadeNaoEncontrada("Usuario nao encontrado"));
        usuarioRepository.delete(usuario);

    }

   public Usuario buscar (Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntidadeNaoEncontrada("Usuario nao encontrado"));
   }


   @Transactional
   public Usuario atualizar (Long usuarioId, Usuario usuario) {
        Usuario usuarioAtual = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NegocioException("Usuario nao encontrado"));


       boolean emailJaExiste = usuarioRepository.findByEmail(usuario.getEmail())
               .filter(u -> u.getId() != null && !u.getId().equals(usuarioId))
               .isPresent();

       if (emailJaExiste) {
           throw new NegocioException("email ja cadastrado");
       }

       usuarioAtual.setNome(usuario.getNome());
       usuarioAtual.setEmail(usuario.getEmail());

       return usuarioRepository.save(usuarioAtual);

   }






}
