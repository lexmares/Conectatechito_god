package com.ito.Marketplace.repository;

import com.ito.Marketplace.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreoInstitucional(String correoInstitucional);
    boolean existsByMatricula(String matricula);
}
