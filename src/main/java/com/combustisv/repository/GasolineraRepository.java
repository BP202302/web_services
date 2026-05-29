package com.combustisv.repository;

import com.combustisv.model.Gasolinera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GasolineraRepository extends JpaRepository<Gasolinera, Long> {
    List<Gasolinera> findByActivaTrue();
    List<Gasolinera> findByMarca(String marca);
    List<Gasolinera> findByDepartamento(String departamento);
}
