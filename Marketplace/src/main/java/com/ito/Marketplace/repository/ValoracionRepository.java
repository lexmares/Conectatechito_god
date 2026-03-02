package com.ito.Marketplace.repository;

import com.ito.Marketplace.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {

    List<Valoracion> findByProducto_IdProducto(Long idProducto);

}