package com.ito.Marketplace.service;

import com.ito.Marketplace.enums.EstadoPedido;
import com.ito.Marketplace.exception.ResourceConflictException;
import com.ito.Marketplace.exception.ResourceNotFoundException;
import com.ito.Marketplace.model.Entrega;
import com.ito.Marketplace.model.Transaccion;
import com.ito.Marketplace.repository.EntregaRepository;
import com.ito.Marketplace.repository.TransaccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final TransaccionRepository transaccionRepository;

    public EntregaService(EntregaRepository entregaRepository,
            TransaccionRepository transaccionRepository) {
        this.entregaRepository = entregaRepository;
        this.transaccionRepository = transaccionRepository;
    }

    public Entrega crearEntrega(Long idTransaccion, Entrega entrega) {
        // Verificar si la transacción existe
        Transaccion transaccion = transaccionRepository.findById(idTransaccion)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Transacción con ID " + idTransaccion + " no encontrada"));

        // Regla: Una transacción solo puede tener una entrega
        entregaRepository.findByTransaccion_IdTransaccion(idTransaccion).ifPresent(e -> {
            throw new ResourceConflictException(
                    "La transacción " + idTransaccion + " ya tiene una entrega programada.");
        });

        entrega.setTransaccion(transaccion);
        entrega.setConfirmacionVendedor(false); // Asegurar valor inicial no nulo
        entrega.setConfirmacionComprador(false); // Asegurar valor inicial no nulo
        return entregaRepository.save(entrega);
    }

    @Transactional
    public Entrega confirmarVendedor(Long idTransaccion) {
        Entrega entrega = entregaRepository.findByTransaccion_IdTransaccion(idTransaccion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Entrega para la transacción " + idTransaccion + " no encontrada"));

        entrega.setConfirmacionVendedor(true);

        // Si el comprador ya confirmó, marcar transacción como entregada
        if (Boolean.TRUE.equals(entrega.getConfirmacionComprador())) {
            actualizarTransaccionEntregada(entrega.getTransaccion());
        }

        return entregaRepository.save(entrega);
    }

    @Transactional
    public Entrega confirmarComprador(Long idTransaccion) {
        Entrega entrega = entregaRepository.findByTransaccion_IdTransaccion(idTransaccion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Entrega para la transacción " + idTransaccion + " no encontrada"));

        entrega.setConfirmacionComprador(true);

        // Si el vendedor ya confirmó, marcar transacción como entregada
        if (Boolean.TRUE.equals(entrega.getConfirmacionVendedor())) {
            actualizarTransaccionEntregada(entrega.getTransaccion());
        }

        return entregaRepository.save(entrega);
    }

    private void actualizarTransaccionEntregada(Transaccion transaccion) {
        transaccion.setEstadoPedido(EstadoPedido.entregado);
        transaccionRepository.save(transaccion);
    }

    public List<Entrega> obtenerTodas() {
        return entregaRepository.findAll();
    }

    public Entrega obtenerPorTransaccion(Long idTransaccion) {
        return entregaRepository
                .findByTransaccion_IdTransaccion(idTransaccion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró una entrega para la transacción " + idTransaccion));
    }
}