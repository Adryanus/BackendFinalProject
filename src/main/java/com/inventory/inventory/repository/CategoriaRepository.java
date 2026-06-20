package com.inventory.inventory.repository;

import com.inventory.inventory.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository
        extends JpaRepository<Categoria, Long> {

}
