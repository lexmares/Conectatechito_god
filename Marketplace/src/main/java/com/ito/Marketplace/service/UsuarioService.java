package com.ito.Marketplace.service;
import com.ito.Marketplace.model.Usuario;
import com.ito.Marketplace.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {

        if (usuarioRepository.existsByCorreoInstitucional(usuario.getCorreoInstitucional())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        if (usuarioRepository.existsByMatricula(usuario.getMatricula())) {
            throw new RuntimeException("La matrícula ya está registrada");
        }

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }


}