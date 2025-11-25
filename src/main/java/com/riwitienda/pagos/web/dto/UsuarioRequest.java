package com.riwitienda.pagos.web.dto;


import com.riwitienda.pagos.domain.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

    public record UsuarioRequest(
            String username,
            String password,
            Role role
    ) {}



