package com.riwitienda.pagos.web.dto;


public class UsuarioResponse {
    private String id;
    private String nombre;

    public UsuarioResponse(String id, String nombre) {
        this.id = id; this.nombre = nombre;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
}
