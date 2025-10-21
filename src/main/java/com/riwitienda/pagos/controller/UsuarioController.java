package com.riwitienda.pagos.controller;

import com.riwitienda.pagos.services.impl.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// entrypoints/rest/EstudianteController.java
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping
    public ResponseEntity<String> crear(@RequestParam String id, @RequestParam String nombre) {
        String respuesta = "ID: " + id + ", Nombre: " + nombre;
        usuarioService.crearUsuario(id,nombre);
        return ResponseEntity.ok(respuesta);
    }


    @GetMapping
    public ResponseEntity<String> obtener(@RequestParam String id) {
        return ResponseEntity.ok("Obtenido usuario con ID: " + id);
    }

    // PUT: Actualizar un usuario existente
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestParam String id, @RequestParam String nuevoNombre) {
        return ResponseEntity.ok("Usuario con ID: " + id + " actualizado a nombre: " + nuevoNombre);
    }

    // DELETE: Eliminar un usuario por ID
    @DeleteMapping
    public ResponseEntity<String> eliminar(@RequestParam String id) {
        return ResponseEntity.ok("Usuario con ID: " + id + " eliminado");
    }
}
