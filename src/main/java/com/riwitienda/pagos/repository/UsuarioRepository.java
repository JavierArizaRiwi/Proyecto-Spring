package com.riwitienda.pagos.repository;


import com.riwitienda.pagos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {


    Optional<Usuario> findByUsername(String username);



}

