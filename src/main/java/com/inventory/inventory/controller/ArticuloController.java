package com.inventory.inventory.controller;

import com.inventory.inventory.dto.ArticuloRequest;
import com.inventory.inventory.dto.ArticuloResponse;
import com.inventory.inventory.model.Articulo;
import com.inventory.inventory.service.ArticuloService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<ArticuloResponse> listar() {

        return service.obtenerTodos();
    }

    @GetMapping("/pagina")
    public Page<ArticuloResponse> listarPaginado(
            Pageable pageable) {

        return service.obtenerPaginadosResponse(
                pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.obtenerResponsePorId(id));
    }

    @PostMapping
    public Articulo crear(
            @Valid @RequestBody ArticuloRequest request) {

        return service.crear(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticuloResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ArticuloRequest request) {

        return ResponseEntity.ok(
                service.actualizar(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ArticuloResponse> actualizarParcial(
            @PathVariable Long id,
            @RequestBody ArticuloRequest request) {

        return ResponseEntity.ok(
                service.actualizarParcial(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.buscarPorId(id);

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public List<Articulo> buscarPorNombre(
            @RequestParam String nombre) {

        return service.buscarPorNombre(nombre);
    }
}
