package com.combustisv.repository;

import com.combustisv.model.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PrecioRepository extends JpaRepository<Precio, Long> {
    List<Precio> findByVigenteTrue();

    @Query("SELECT p FROM Precio p WHERE p.vigente = true AND p.tipoCombustible.id = :tipoId")
    List<Precio> findByTipoCombustibleVigente(@Param("tipoId") Long tipoId);

    @Query("SELECT p FROM Precio p WHERE p.vigente = true AND p.gasolinera.id = :gasId")
    List<Precio> findByGasolineraVigente(@Param("gasId") Long gasId);

    @Query("SELECT p FROM Precio p WHERE p.vigente = true ORDER BY p.precioGalon ASC")
    List<Precio> findAllVigentesOrdenadosPorPrecio();
}
