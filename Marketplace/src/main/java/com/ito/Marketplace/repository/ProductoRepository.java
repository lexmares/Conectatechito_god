package com.ito.Marketplace.repository;

import com.ito.Marketplace.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Obtener productos por vendedor
    List<Producto> findByVendedorIdUsuario(Long idUsuario);
}
