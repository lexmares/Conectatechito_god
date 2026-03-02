package com.ito.Marketplace.controller;

import com.ito.Marketplace.model.Entrega;
import com.ito.Marketplace.service.EntregaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping("/{idTransaccion}")
    public Entrega crearEntrega(@PathVariable Long idTransaccion,
                                @RequestBody Entrega entrega) {
        return entregaService.crearEntrega(idTransaccion, entrega);
    }

    @PutMapping("/confirmar-vendedor/{idTransaccion}")
    public Entrega confirmarVendedor(@PathVariable Long idTransaccion) {
        return entregaService.confirmarVendedor(idTransaccion);
    }

    @PutMapping("/confirmar-comprador/{idTransaccion}")
    public Entrega confirmarComprador(@PathVariable Long idTransaccion) {
        return entregaService.confirmarComprador(idTransaccion);
    }
}