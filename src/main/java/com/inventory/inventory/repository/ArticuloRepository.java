package com.inventory.inventory.repository;

import com.inventory.inventory.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticuloRepository
        extends JpaRepository<Articulo, Long> {

    List<Articulo> findByNombreContainingIgnoreCase(
            String nombre);
}
