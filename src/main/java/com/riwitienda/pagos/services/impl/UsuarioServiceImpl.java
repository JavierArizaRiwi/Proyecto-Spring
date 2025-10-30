package com.riwitienda.pagos.services.impl;



import com.riwitienda.pagos.repository.UsuarioRepository;
import com.riwitienda.pagos.entity.UsuarioEntity;
import com.riwitienda.pagos.web.dto.UsuarioRequest;
import com.riwitienda.pagos.web.dto.UsuarioResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository repo;

    public UsuarioServiceImpl(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Transactional
    @Override
    public UsuarioResponse crear(UsuarioRequest req) {

        if (repo.existsByNombreIgnoreCase(req.getNombre())) {
            throw new IllegalArgumentException("nombre duplicado");
        }
        var e = new UsuarioEntity();
        e.setNombre(req.getNombre());
        e.setApellido(req.getApellido());
        e.setEmail(req.getEmail());
        e.setTelefono(req.getTelefono());
        e.setCargo(req.getCargo());
        var saved = repo.save(e);
        return new UsuarioResponse(saved.getId(), saved.getNombre(),saved.getApellido(),saved.getEmail(), saved.getTelefono(),saved.getCargo());
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioResponse obtenerPorId(String id) {
        var e = repo.findById(id).orElseThrow(() -> new NoSuchElementException("usuario no encontrado"));
        return new UsuarioResponse(e.getId(), e.getNombre(),e.getApellido(),e.getEmail(), e.getTelefono(),e.getCargo());
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioResponse> listar() {
        return repo.findAll().stream()
                .map(e -> new UsuarioResponse(e.getId(), e.getNombre(),e.getApellido(),e.getEmail(), e.getTelefono(),e.getCargo()))
                .toList();
    }
}
