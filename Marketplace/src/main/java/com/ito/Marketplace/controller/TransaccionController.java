package com.ito.Marketplace.controller;

import com.ito.Marketplace.model.Transaccion;
import com.ito.Marketplace.service.TransaccionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    // POST - Comprar producto
    @PostMapping
    public Transaccion crearTransaccion(@RequestBody Transaccion transaccion) {
        return transaccionService.crearTransaccion(transaccion);
    }

    // GET - Ver compras de un usuario
    @GetMapping("/usuario/{id}")
    public List<Transaccion> obtenerCompras(@PathVariable Long id) {
        return transaccionService.obtenerComprasPorUsuario(id);
    }
}