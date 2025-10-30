package com.riwitienda.pagos.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada inmutable para crear/actualizar Usuario.
 * Con Spring Boot 3 + Jackson, los records se serializan/deserializan sin config extra.
 */
public record UsuarioRecord(
        Long id, // puede ser null si es "create"
        @NotBlank(message = "nombre es obligatorio")
        @Size(min = 3, max = 50, message = "nombre debe tener entre 3 y 50 caracteres")
        String nombre
) { }
