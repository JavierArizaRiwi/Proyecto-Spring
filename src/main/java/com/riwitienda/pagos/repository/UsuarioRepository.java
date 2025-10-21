package com.riwitienda.pagos.repository;


import com.riwitienda.pagos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {


}

