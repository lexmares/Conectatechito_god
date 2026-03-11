package com.ito.Marketplace.service;

import com.ito.Marketplace.model.Entrega;
import com.ito.Marketplace.model.Transaccion;
import com.ito.Marketplace.repository.EntregaRepository;
import com.ito.Marketplace.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        Transaccion transaccion = transaccionRepository.findById(idTransaccion)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        entrega.setTransaccion(transaccion);

        return entregaRepository.save(entrega);
    }

    public Entrega confirmarVendedor(Long idTransaccion) {

        Entrega entrega = entregaRepository.findByTransaccion_IdTransaccion(idTransaccion)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));

        entrega.setConfirmacionVendedor(true);

        return entregaRepository.save(entrega);
    }

    public Entrega confirmarComprador(Long idTransaccion) {

        Entrega entrega = entregaRepository.findByTransaccion_IdTransaccion(idTransaccion)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));

        entrega.setConfirmacionComprador(true);

        return entregaRepository.save(entrega);
    }
    public List<Entrega> obtenerTodas() {
        return entregaRepository.findAll();
    }

    public Entrega obtenerPorTransaccion(Long idTransaccion) {
        return entregaRepository
                .findByTransaccion_IdTransaccion(idTransaccion)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));
    }


}