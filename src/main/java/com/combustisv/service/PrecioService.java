package com.combustisv.service;

import com.combustisv.model.Precio;
import com.combustisv.repository.PrecioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PrecioService {
    @Autowired
    private PrecioRepository repo;

    public List<Precio> listarTodos() { return repo.findAll(); }
    public List<Precio> listarVigentes() { return repo.findByVigenteTrue(); }
    public List<Precio> listarPorTipo(Long tipoId) { return repo.findByTipoCombustibleVigente(tipoId); }
    public List<Precio> listarPorGasolinera(Long gasId) { return repo.findByGasolineraVigente(gasId); }
    public List<Precio> listarOrdenadosPorPrecio() { return repo.findAllVigentesOrdenadosPorPrecio(); }
    public Optional<Precio> buscarPorId(Long id) { return repo.findById(id); }
    public Precio guardar(Precio p) { return repo.save(p); }
    public void eliminar(Long id) { repo.deleteById(id); }
}
