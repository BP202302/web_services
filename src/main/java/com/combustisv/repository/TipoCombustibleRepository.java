package com.combustisv.repository;

import com.combustisv.model.TipoCombustible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TipoCombustibleRepository extends JpaRepository<TipoCombustible, Long> {
    Optional<TipoCombustible> findByNombre(String nombre);
}
