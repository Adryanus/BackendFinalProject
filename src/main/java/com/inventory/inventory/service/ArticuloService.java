package com.inventory.inventory.service;

import com.inventory.inventory.dto.ArticuloRequest;
import com.inventory.inventory.exception.RecursoNoEncontradoException;
import com.inventory.inventory.model.Articulo;
import com.inventory.inventory.model.Categoria;
import com.inventory.inventory.repository.ArticuloRepository;
import com.inventory.inventory.repository.CategoriaRepository;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.inventory.dto.ArticuloResponse;

import java.util.List;

@Service
public class ArticuloService {

    private final ArticuloRepository repository;
    private final CategoriaRepository categoriaRepository;

    public ArticuloService(
            ArticuloRepository repository,
            CategoriaRepository categoriaRepository) {

        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ArticuloResponse> obtenerTodos() {

        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public Articulo buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe un artículo con ID " + id));
    }

    public Articulo guardar(Articulo articulo) {
        return repository.save(articulo);
    }

    public Articulo crear(ArticuloRequest request) {

        Categoria categoria = categoriaRepository.findById(
                request.getCategoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una categoría con ID "
                                + request.getCategoriaId()));

        Articulo articulo = new Articulo();

        articulo.setNombre(
                request.getNombre());

        articulo.setPrecio(
                request.getPrecio());

        articulo.setStock(
                request.getStock());

        articulo.setCategoria(
                categoria);

        return repository.save(
                articulo);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Articulo> buscarPorNombre(
            String nombre) {

        return repository
                .findByNombreContainingIgnoreCase(
                        nombre);
    }

    public Page<Articulo> obtenerPaginados(
            Pageable pageable) {

        return repository.findAll(pageable);
    }

    private ArticuloResponse convertirAResponse(
            Articulo articulo) {

        ArticuloResponse response = new ArticuloResponse();

        response.setId(
                articulo.getId());

        response.setNombre(
                articulo.getNombre());

        response.setPrecio(
                articulo.getPrecio());

        response.setStock(
                articulo.getStock());

        if (articulo.getCategoria() != null) {

            response.setCategoriaId(
                    articulo.getCategoria().getId());

            response.setCategoriaNombre(
                    articulo.getCategoria().getNombre());
        }

        return response;
    }

    public ArticuloResponse obtenerResponsePorId(
            Long id) {

        Articulo articulo = buscarPorId(id);

        return convertirAResponse(
                articulo);
    }

    public Page<ArticuloResponse> obtenerPaginadosResponse(
            Pageable pageable) {

        return repository.findAll(pageable)
                .map(this::convertirAResponse);
    }

    public ArticuloResponse actualizar(
            Long id,
            ArticuloRequest request) {

        Articulo articulo = buscarPorId(id);

        Categoria categoria = categoriaRepository.findById(
                request.getCategoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe una categoría con ID "
                                + request.getCategoriaId()));

        articulo.setNombre(
                request.getNombre());

        articulo.setPrecio(
                request.getPrecio());

        articulo.setStock(
                request.getStock());

        articulo.setCategoria(
                categoria);

        Articulo actualizado = repository.save(articulo);

        return convertirAResponse(
                actualizado);
    }
}