package com.combustisv.service;

import com.combustisv.model.Gasolinera;
import com.combustisv.repository.GasolineraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GasolineraService {
    @Autowired
    private GasolineraRepository repo;

    public List<Gasolinera> listarTodas() { return repo.findAll(); }
    public List<Gasolinera> listarActivas() { return repo.findByActivaTrue(); }
    public Optional<Gasolinera> buscarPorId(Long id) { return repo.findById(id); }
    public Gasolinera guardar(Gasolinera g) { return repo.save(g); }
    public void eliminar(Long id) { repo.deleteById(id); }
    public List<Gasolinera> buscarPorMarca(String marca) { return repo.findByMarca(marca); }
}
