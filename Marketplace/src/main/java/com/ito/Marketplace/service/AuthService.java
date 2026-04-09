package com.ito.Marketplace.service;

import com.ito.Marketplace.model.Usuario;
import com.ito.Marketplace.repository.UsuarioRepository;
import com.ito.Marketplace.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Map<String, Object> login(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreoInstitucional(correo);

        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            String token = jwtUtil.generateToken(usuario.getCorreoInstitucional());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("usuario", usuario.getNombreCompleto());
            return response;
        }

        return null; // credenciales inválidas
    }
}
