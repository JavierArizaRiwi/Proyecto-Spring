package com.riwitienda.pagos.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {
    @Id
    private String id;

    @Column(nullable = false, length = 50)
    private String nombre;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}

