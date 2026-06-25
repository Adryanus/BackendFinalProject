package com.inventory.inventory.service;

import com.inventory.inventory.dto.ArticuloRequest;
import com.inventory.inventory.dto.ArticuloResponse;
import com.inventory.inventory.exception.RecursoNoEncontradoException;
import com.inventory.inventory.model.Articulo;
import com.inventory.inventory.model.Categoria;
import com.inventory.inventory.repository.ArticuloRepository;
import com.inventory.inventory.repository.CategoriaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "No existe un artículo con ID " + id));
    }

    public ArticuloResponse obtenerResponsePorId(Long id) {

        return convertirAResponse(
                buscarPorId(id));
    }

    public Articulo crear(ArticuloRequest request) {

        Categoria categoria =
                categoriaRepository.findById(
                        request.getCategoriaId())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "No existe una categoría con ID "
                                        + request.getCategoriaId()));

        Articulo articulo = new Articulo();

        articulo.setNombre(request.getNombre());
        articulo.setPrecio(request.getPrecio());
        articulo.setStock(request.getStock());
        articulo.setCategoria(categoria);

        return repository.save(articulo);
    }

    // ==========================
    // PUT
    // ==========================

    public ArticuloResponse actualizar(
            Long id,
            ArticuloRequest request) {

        Articulo articulo = buscarPorId(id);

        copiarCampos(
                articulo,
                request,
                false);

        return convertirAResponse(
                repository.save(articulo));
    }

    // ==========================
    // PATCH
    // ==========================

    public ArticuloResponse actualizarParcial(
            Long id,
            ArticuloRequest request) {

        Articulo articulo = buscarPorId(id);

        copiarCampos(
                articulo,
                request,
                true);

        return convertirAResponse(
                repository.save(articulo));
    }

    // ==========================
    // Método compartido
    // ==========================

    private void copiarCampos(
            Articulo articulo,
            ArticuloRequest request,
            boolean parcial) {

        if (!parcial ||
                (request.getNombre() != null
                        && !request.getNombre().isBlank())) {

            articulo.setNombre(
                    request.getNombre());
        }

        if (!parcial ||
                request.getPrecio() != null) {

            articulo.setPrecio(
                    request.getPrecio());
        }

        if (!parcial ||
                request.getStock() != null) {

            articulo.setStock(
                    request.getStock());
        }

        if (!parcial ||
                request.getCategoriaId() != null) {

            Categoria categoria =
                    categoriaRepository.findById(
                            request.getCategoriaId())
                    .orElseThrow(() ->
                            new RecursoNoEncontradoException(
                                    "No existe una categoría con ID "
                                            + request.getCategoriaId()));

            articulo.setCategoria(
                    categoria);
        }
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

    public Page<ArticuloResponse> obtenerPaginadosResponse(
            Pageable pageable) {

        return repository.findAll(pageable)
                .map(this::convertirAResponse);
    }

    private ArticuloResponse convertirAResponse(
            Articulo articulo) {

        ArticuloResponse response =
                new ArticuloResponse();

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
}