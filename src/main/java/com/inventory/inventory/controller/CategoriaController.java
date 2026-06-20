package com.inventory.inventory.controller;

import com.inventory.inventory.model.Categoria;
import com.inventory.inventory.service.CategoriaService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(
            CategoriaService service) {

        this.service = service;
    }

    @GetMapping
    public List<Categoria> listar() {

        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(
            @PathVariable Long id) {

        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categoria crear(
            @Valid
            @RequestBody Categoria categoria) {

        return service.guardar(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        if (service.obtenerPorId(id).isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
