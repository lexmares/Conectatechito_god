package com.ito.Marketplace.service;

import com.ito.Marketplace.exception.ResourceConflictException;
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
            throw new ResourceConflictException("El correo " + usuario.getCorreoInstitucional() + " ya está registrado.");
        }

        if (usuarioRepository.existsByMatricula(usuario.getMatricula())) {
            throw new ResourceConflictException("La matrícula " + usuario.getMatricula() + " ya está registrada.");
        }

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
}