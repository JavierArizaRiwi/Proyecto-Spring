package com.riwitienda.pagos.config;

import com.riwitienda.pagos.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
/**
 * Configuración de seguridad de la aplicación.
 * - Habilita seguridad basada en JWT (filtro `JwtAuthenticationFilter`).
 * - Declara beans comunes: `AuthenticationManager`, `SecurityFilterChain` y `PasswordEncoder`.
 */
public class SecurityConfig {

    /** Filtro que extrae y valida el JWT en cada petición. */
    private final JwtAuthenticationFilter jwtFilter;

    /** Servicio para cargar usuarios desde la capa de persistencia. */
    private final UserDetailsService userDetailsService;

    // NOTE: no almacenamos AuthenticationConfiguration como campo; lo inyectamos sólo en el bean.

    /**
     * Bean que expone el `AuthenticationManager` usado por Spring Security.
     * Se delega en la `AuthenticationConfiguration` para obtener la implementación configurada.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configuración principal del filtro de seguridad.
     * - Deshabilita CSRF (API REST)
     * - Política stateless para usar JWT
     * - Permite `/auth/**` sin autenticación y protege el resto
     * - Añade el filtro JWT antes del filtro estándar de username/password
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Bean para encriptar contraseñas en la BD usando BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

