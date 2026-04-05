package com.ito.Marketplace.service;

import com.ito.Marketplace.enums.EstadoPedido;
import com.ito.Marketplace.exception.BadRequestException;
import com.ito.Marketplace.exception.ResourceNotFoundException;
import com.ito.Marketplace.model.Producto;
import com.ito.Marketplace.model.Transaccion;
import com.ito.Marketplace.model.Usuario;
import com.ito.Marketplace.repository.ProductoRepository;
import com.ito.Marketplace.repository.TransaccionRepository;
import com.ito.Marketplace.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public TransaccionService(TransaccionRepository transaccionRepository,
            ProductoRepository productoRepository,
            UsuarioRepository usuarioRepository) {
        this.transaccionRepository = transaccionRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Transaccion crearTransaccion(Transaccion transaccion) {

        Producto producto = productoRepository.findByIdForUpdate(
                transaccion.getProducto().getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto con ID " + transaccion.getProducto().getIdProducto() + " no encontrado"));

        if (producto.getVendedor().getIdUsuario()
                .equals(transaccion.getComprador().getIdUsuario())) {
            throw new BadRequestException("No puedes comprar tu propio producto.");
        }

        if (transaccion.getCantidad() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor a cero.");
        }

        if (producto.getStock() < transaccion.getCantidad()) {
            throw new BadRequestException("Stock insuficiente. Disponible: " + producto.getStock());
        }

        // Restar cantidad comprada
        producto.setStock(producto.getStock() - transaccion.getCantidad());

        if (producto.getStock() == 0) {
            producto.setDisponibilidad(false);
        }

        // Cargar el comprador completo para que no devuelva nulos en el JSON
        Usuario compradorCompleto = usuarioRepository.findById(transaccion.getComprador().getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Comprador no encontrado"));

        // Asignar los objetos completos a la transacción
        transaccion.setProducto(producto);
        transaccion.setComprador(compradorCompleto);
        transaccion.setEstadoPedido(EstadoPedido.pagado);
        transaccion.setFechaCompra(LocalDateTime.now());

        return transaccionRepository.save(transaccion);
    }

    public Transaccion cancelarTransaccion(Long idTransaccion) {

        Transaccion transaccion = transaccionRepository.findById(idTransaccion)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Transacción con ID " + idTransaccion + " no encontrada"));

        if (transaccion.getEstadoPedido() == EstadoPedido.entregado) {
            throw new BadRequestException("No se puede cancelar una transacción ya entregada.");
        }

        if (transaccion.getEstadoPedido() == EstadoPedido.cancelado) {
            throw new BadRequestException("La transacción ya se encuentra cancelada.");
        }

        Producto producto = transaccion.getProducto();

        // Devolver unidad(es)
        producto.setStock(producto.getStock() + transaccion.getCantidad());

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