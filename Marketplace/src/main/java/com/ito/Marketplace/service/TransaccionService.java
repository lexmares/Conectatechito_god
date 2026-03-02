package com.ito.Marketplace.service;

import com.ito.Marketplace.enums.EstadoPedido;
import com.ito.Marketplace.model.Producto;
import com.ito.Marketplace.model.Transaccion;
import com.ito.Marketplace.repository.ProductoRepository;
import com.ito.Marketplace.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final ProductoRepository productoRepository;

    public TransaccionService(TransaccionRepository transaccionRepository,
                              ProductoRepository productoRepository) {
        this.transaccionRepository = transaccionRepository;
        this.productoRepository = productoRepository;
    }

    public Transaccion crearTransaccion(Transaccion transaccion) {

        Producto producto = productoRepository.findById(
                transaccion.getProducto().getIdProducto()
        ).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() <= 0) {
            throw new RuntimeException("Producto sin stock disponible");
        }

        if (producto.getVendedor().getIdUsuario()
                .equals(transaccion.getComprador().getIdUsuario())) {
            // evita que el usuario se compre un producto a si mismo
            throw new RuntimeException("No puedes comprar tu propio producto");
        }

        // Restar 1 unidad
        producto.setStock(producto.getStock() - 1);

        if (producto.getStock() == 0) {
            producto.setDisponibilidad(false);
        }

        productoRepository.save(producto);

        transaccion.setEstadoPedido(EstadoPedido.pagado);
        transaccion.setFechaCompra(LocalDateTime.now());

        return transaccionRepository.save(transaccion);
    }
    public Transaccion cancelarTransaccion(Long idTransaccion) {

        Transaccion transaccion = transaccionRepository.findById(idTransaccion)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        if (transaccion.getEstadoPedido() == EstadoPedido.entregado) {
            throw new RuntimeException("No se puede cancelar una transacción entregada");
        }

        if (transaccion.getEstadoPedido() == EstadoPedido.cancelado) {
            throw new RuntimeException("La transacción ya está cancelada");
        }

        Producto producto = transaccion.getProducto();

        // Devolver 1 unidad
        producto.setStock(producto.getStock() + 1);

        if (producto.getStock() > 0) {
            producto.setDisponibilidad(true);
        }

        productoRepository.save(producto);

        transaccion.setEstadoPedido(EstadoPedido.cancelado);

        return transaccionRepository.save(transaccion);
    }


    public List<Transaccion> obtenerComprasPorUsuario(Long idUsuario) {
        return transaccionRepository.findByComprador_IdUsuario(idUsuario);
    }
}