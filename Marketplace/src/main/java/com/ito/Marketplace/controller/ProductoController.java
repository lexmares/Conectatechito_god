package com.ito.Marketplace.controller;

import com.ito.Marketplace.model.Producto;
import com.ito.Marketplace.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/{idVendedor}")
    public Producto registrarProducto(@RequestBody Producto producto,
                                      @PathVariable Long idVendedor) {
        return productoService.crearProducto(producto, idVendedor);
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/vendedor/{idUsuario}")
    public List<Producto> listarPorVendedor(@PathVariable Long idUsuario) {
        return productoService.obtenerPorVendedor(idUsuario);
    }
}
