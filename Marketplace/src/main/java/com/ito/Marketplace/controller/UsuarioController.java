package com.ito.Marketplace.controller;
import com.ito.Marketplace.model.Usuario;
import com.ito.Marketplace.service.TransaccionService;
import com.ito.Marketplace.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TransaccionService transaccionService;

    public UsuarioController(UsuarioService usuarioService, TransaccionService transaccionService) {
        this.usuarioService = usuarioService;
        this.transaccionService = transaccionService;
    }

    @PostMapping
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        //System.out.println(usuario.getCorreoInstitucional());
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping("/test")
    public String test() {
        System.out.println("ENTRO AL TEST");
        return "OK";
    }



}