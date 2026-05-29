package com.combustisv.service;

import com.combustisv.model.TipoCombustible;
import com.combustisv.repository.TipoCombustibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoCombustibleService {
    @Autowired
    private TipoCombustibleRepository repo;

    public List<TipoCombustible> listarTodos() { return repo.findAll(); }
    public Optional<TipoCombustible> buscarPorId(Long id) { return repo.findById(id); }
    public TipoCombustible guardar(TipoCombustible t) { return repo.save(t); }
    public void eliminar(Long id) { repo.deleteById(id); }
}
