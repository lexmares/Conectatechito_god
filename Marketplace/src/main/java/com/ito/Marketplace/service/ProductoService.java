package com.ito.Marketplace.service;

import com.ito.Marketplace.model.Producto;
import com.ito.Marketplace.model.Usuario;
import com.ito.Marketplace.repository.ProductoRepository;
import com.ito.Marketplace.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public ProductoService(ProductoRepository productoRepository,
                           UsuarioRepository usuarioRepository) {
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Producto crearProducto(Producto producto, Long idVendedor) {

        Usuario vendedor = usuarioRepository.findById(idVendedor)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        producto.setVendedor(vendedor);

        if (producto.getFechaPublicacion() == null) {
            producto.setFechaPublicacion(LocalDate.now());
        }

        return productoRepository.save(producto);
    }

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> obtenerPorVendedor(Long idUsuario) {
        return productoRepository.findByVendedorIdUsuario(idUsuario);
    }
}
