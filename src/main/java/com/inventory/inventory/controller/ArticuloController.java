package com.inventory.inventory.controller;

import com.inventory.inventory.model.Articulo;
import com.inventory.inventory.service.ArticuloService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulos")
public class ArticuloController {

    private final ArticuloService service;

    public ArticuloController(ArticuloService service) {
        this.service = service;
    }

    @GetMapping
    public List<Articulo> listar() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id));
    }

    @PostMapping
    public Articulo crear(
            @Valid @RequestBody Articulo articulo) {

        return service.guardar(articulo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Articulo articulo) {

        service.buscarPorId(id);

        articulo.setId(id);

        return ResponseEntity.ok(
                service.guardar(articulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.buscarPorId(id);

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
