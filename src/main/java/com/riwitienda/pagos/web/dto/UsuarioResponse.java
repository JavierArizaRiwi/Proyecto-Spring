package com.riwitienda.pagos.web.dto;


public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String cargo;

    public UsuarioResponse(Long id, String nombre,String apellido,String email,String telefono,String cargo) {
        this.id = id; this.nombre = nombre;this.apellido=apellido;this.email=email;this.telefono=telefono;this.cargo=cargo;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
