package com.inventory.inventory.service;

import com.inventory.inventory.model.Articulo;
import com.inventory.inventory.repository.ArticuloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {

    private final ArticuloRepository repository;

    public ArticuloService(ArticuloRepository repository) {
        this.repository = repository;
    }

    public List<Articulo> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Articulo> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Articulo guardar(Articulo articulo) {
        return repository.save(articulo);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
