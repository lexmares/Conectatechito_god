package com.ito.Marketplace.repository;

import com.ito.Marketplace.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByComprador_IdUsuario(Long idUsuario);

}