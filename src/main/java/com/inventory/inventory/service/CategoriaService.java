package com.inventory.inventory.service;

import com.inventory.inventory.model.Categoria;
import com.inventory.inventory.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(
            CategoriaRepository repository) {

        this.repository = repository;
    }

    public List<Categoria> obtenerTodas() {

        return repository.findAll();
    }

    public Optional<Categoria> obtenerPorId(
            Long id) {

        return repository.findById(id);
    }

    public Categoria guardar(
            Categoria categoria) {

        return repository.save(categoria);
    }

    public void eliminar(
            Long id) {

        repository.deleteById(id);
    }
}
