package com.riwitienda.pagos.repository;


import com.riwitienda.pagos.domain.Usuario;
import com.riwitienda.pagos.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {

    boolean existsByNombreIgnoreCase(String nombre);


}

