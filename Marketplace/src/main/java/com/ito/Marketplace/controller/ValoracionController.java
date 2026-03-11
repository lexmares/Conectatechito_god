package com.ito.Marketplace.controller;

import com.ito.Marketplace.model.Valoracion;
import com.ito.Marketplace.service.ValoracionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valoraciones")
public class ValoracionController {

    private final ValoracionService valoracionService;

    public ValoracionController(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }

    @PostMapping("/transaccion/{idTransaccion}")
    public Valoracion crearValoracion(@PathVariable Long idTransaccion,
                                      @RequestBody Valoracion valoracion) {
        return valoracionService.crearValoracion(idTransaccion, valoracion);
    }


    @GetMapping("/producto/{idProducto}")
    public List<Valoracion> obtenerPorProducto(@PathVariable Long idProducto) {
        return valoracionService.obtenerPorProducto(idProducto);
    }
}