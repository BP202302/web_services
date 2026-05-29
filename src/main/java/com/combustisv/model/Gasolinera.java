package com.combustisv.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "gasolineras")
public class Gasolinera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La direccion es obligatoria")
    @Column(nullable = false)
    private String direccion;

    private String departamento;
    private String municipio;
    private String telefono;
    private String marca;
    private Boolean activa = true;

    @OneToMany(mappedBy = "gasolinera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Precio> precios;

    public Gasolinera() {}
    public Gasolinera(String nombre, String direccion, String marca) {
        this.nombre = nombre; this.direccion = direccion; this.marca = marca; this.activa = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
    public List<Precio> getPrecios() { return precios; }
    public void setPrecios(List<Precio> precios) { this.precios = precios; }
}
