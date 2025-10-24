package com.riwitienda.pagos.web.dto;


public class UsuarioResponse {
    private Long id;
    private String nombre;

    public UsuarioResponse(Long id, String nombre) {
        this.id = id; this.nombre = nombre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
}
