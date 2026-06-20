package com.inventory.inventory.dto;

import jakarta.validation.constraints.*;

public class ArticuloRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Positive(message = "El precio debe ser mayor que cero")
    private double precio;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    private int stock;

    @NotNull(message = "La categoría es obligatoria")
    private Long categoriaId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
