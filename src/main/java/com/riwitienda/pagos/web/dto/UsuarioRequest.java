package com.riwitienda.pagos.web.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {
    //@NotBlank(message = "id es obligatorio")
    private String id;

    @NotBlank(message = "nombre es obligatorio")
    @Size(min = 3, max = 50, message = "nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
