package com.riwitienda.pagos.web.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {
    //@NotBlank(message = "id es obligatorio")
    private Long id;

    @NotBlank(message = "nombre es obligatorio")
    @Size(min = 3, max = 50, message = "nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
