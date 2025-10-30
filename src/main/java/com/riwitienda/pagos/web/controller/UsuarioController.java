package com.riwitienda.pagos.web.controller;

import com.riwitienda.pagos.services.impl.UsuarioServiceImpl;
import com.riwitienda.pagos.web.dto.UsuarioRecord;
import com.riwitienda.pagos.web.dto.UsuarioRequest;
import com.riwitienda.pagos.web.dto.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Usuarios",
        description = "Controlador que gestiona las operaciones CRUD sobre los usuarios del sistema."
)
@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioServiceImpl service;

    public UsuarioController(UsuarioServiceImpl service) {
        this.service = service;
    }

    // --- CREAR USUARIO ---
    @Operation(
            summary = "Crear usuario",
            description = "Registra un nuevo usuario en el sistema con validaciones de datos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente",
                            content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos",
                            content = @Content),
                    @ApiResponse(responseCode = "409", description = "Usuario ya existente", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(
            @Valid
            @RequestBody
            @Parameter(description = "Datos del usuario a registrar", required = true)
            UsuarioRequest req
    ) {
        var res = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    // --- CREAR USUARIO (con record) ---
    @Operation(
            summary = "Crear usuario (Record)",
            description = "Ejemplo alternativo usando un record Java como DTO.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
                    @ApiResponse(responseCode = "400", description = "Error en la estructura del request")
            }
    )
    @PostMapping("/record")
    public ResponseEntity<UsuarioResponse> crearRecord(
            @Valid
            @RequestBody
            @Parameter(description = "Estructura Record para creación de usuario")
            UsuarioRecord req
    ) {
        Long id = req.id();
        String nombre = req.nombre();
        // lógica de ejemplo
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // --- OBTENER POR ID ---
    @Operation(
            summary = "Obtener usuario por ID",
            description = "Consulta los datos de un usuario existente por su identificador único.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                            content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtener(
            @Parameter(description = "Identificador único del usuario", example = "1")
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    // --- LISTAR TODOS ---
    @Operation(
            summary = "Listar todos los usuarios",
            description = "Devuelve una lista con todos los usuarios registrados en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado completo",
                            content = @Content(schema = @Schema(implementation = UsuarioResponse.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
