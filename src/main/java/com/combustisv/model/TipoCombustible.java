package com.combustisv.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tipos_combustible")
public class TipoCombustible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del tipo es obligatorio")
    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;
    private String unidad = "galon";

    public TipoCombustible() {}
    public TipoCombustible(String nombre, String descripcion) {
        this.nombre = nombre; this.descripcion = descripcion; this.unidad = "galon";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
}
