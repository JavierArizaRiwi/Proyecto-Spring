package com.riwitienda.pagos.web.controller;

import com.riwitienda.pagos.domain.Usuario;
import com.riwitienda.pagos.repository.UsuarioRepository;
import com.riwitienda.pagos.security.JwtService;
import com.riwitienda.pagos.web.dto.AuthResponse;
import com.riwitienda.pagos.web.dto.LoginRequest;
import com.riwitienda.pagos.web.dto.UsuarioRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UsuarioRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody UsuarioRequest request) {
        Usuario user = Usuario.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                );

        authenticationManager.authenticate(authToken);

        Usuario user = userRepository.findByUsername(request.username())
                .orElseThrow(); // aquí luego puedes poner tu excepción custom

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
