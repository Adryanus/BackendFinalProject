package com.inventory.inventory.repository;

import com.inventory.inventory.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

}
