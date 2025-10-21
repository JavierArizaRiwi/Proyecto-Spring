package com.riwitienda.pagos.services.impl;


import com.riwitienda.pagos.domain.Usuario;
import com.riwitienda.pagos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario crearUsuario(String id, String nombre) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        return repository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuario(String id) {
        return repository.findById(id);
    }

    public Optional<Usuario> actualizarUsuario(String id, String nuevoNombre) {
        return repository.findById(id).map(usuario -> {
            usuario.setNombre(nuevoNombre);
            return repository.save(usuario);
        });
    }

    public boolean eliminarUsuario(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
