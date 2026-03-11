package com.ito.Marketplace.service;

import com.ito.Marketplace.enums.EstadoPedido;
import com.ito.Marketplace.model.Transaccion;
import com.ito.Marketplace.model.Valoracion;
import com.ito.Marketplace.repository.TransaccionRepository;
import com.ito.Marketplace.repository.ValoracionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionService {

    private final ValoracionRepository valoracionRepository;
    private final TransaccionRepository transaccionRepository;

    public ValoracionService(ValoracionRepository valoracionRepository,
                             TransaccionRepository transaccionRepository) {
        this.valoracionRepository = valoracionRepository;
        this.transaccionRepository = transaccionRepository;
    }

    public Valoracion crearValoracion(Long idTransaccion, Valoracion valoracion) {
        Transaccion transaccion = transaccionRepository.findById(idTransaccion)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        if (transaccion.getEstadoPedido() != EstadoPedido.entregado) {
            throw new RuntimeException("Solo se puede valorar una compra entregada");
        }

        // Asignar comprador y producto directamente desde la transacción
        valoracion.setUsuario(transaccion.getComprador());
        valoracion.setProducto(transaccion.getProducto());

        return valoracionRepository.save(valoracion);
    }


    public List<Valoracion> obtenerPorProducto(Long idProducto) {
        return valoracionRepository.findByProducto_IdProducto(idProducto);
    }
}