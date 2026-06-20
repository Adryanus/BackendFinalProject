package com.inventory.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(
        min = 3,
        max = 50,
        message = "El nombre debe tener entre 3 y 50 caracteres"
    )
    @Pattern(
        regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 ]+$",
        message = "El nombre contiene caracteres inválidos"
    )
    private String nombre;

    @Positive(message = "El precio debe ser mayor que cero")
    @Max(
        value = 100000000,
        message = "El precio supera el máximo permitido"
    )
    private double precio;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    @Max(
        value = 100000,
        message = "El stock supera el máximo permitido"
    )
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Articulo() {
    }

    public Articulo(Long id,
                    String nombre,
                    double precio,
                    int stock,
                    Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
