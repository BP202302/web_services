package com.combustisv.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "precios")
public class Precio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gasolinera_id", nullable = false)
    private Gasolinera gasolinera;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_combustible_id", nullable = false)
    private TipoCombustible tipoCombustible;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal precioGalon;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion = LocalDate.now();

    private Boolean vigente = true;

    public Precio() {}
    public Precio(Gasolinera g, TipoCombustible t, BigDecimal p) {
        this.gasolinera = g; this.tipoCombustible = t; this.precioGalon = p;
        this.fechaActualizacion = LocalDate.now(); this.vigente = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Gasolinera getGasolinera() { return gasolinera; }
    public void setGasolinera(Gasolinera g) { this.gasolinera = g; }
    public TipoCombustible getTipoCombustible() { return tipoCombustible; }
    public void setTipoCombustible(TipoCombustible t) { this.tipoCombustible = t; }
    public BigDecimal getPrecioGalon() { return precioGalon; }
    public void setPrecioGalon(BigDecimal p) { this.precioGalon = p; }
    public LocalDate getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDate f) { this.fechaActualizacion = f; }
    public Boolean getVigente() { return vigente; }
    public void setVigente(Boolean v) { this.vigente = v; }
}
