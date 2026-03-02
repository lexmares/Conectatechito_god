package com.ito.Marketplace.repository;

import com.ito.Marketplace.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    Optional<Entrega> findByTransaccion_IdTransaccion(Long idTransaccion);

}