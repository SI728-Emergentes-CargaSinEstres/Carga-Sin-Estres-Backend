package com.upc.cargasinestres.CargaSinEstres.Security.config;

import com.upc.cargasinestres.CargaSinEstres.Security.jwt.filter.TokenAuthenticationFilter;
import com.upc.cargasinestres.CargaSinEstres.Security.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class that contains the configuration for the security in the backend application
 * @author Grupo1*/
@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    public SecurityConfig(
            TokenAuthenticationFilter tokenAuthenticationFilter,
            CustomUserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Bean that manages the security configuration
     * @param http Object HttpSecurity
     * @return Object SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);

        authenticationManager = builder.build();
        http.authenticationManager(authenticationManager);

        http.csrf(AbstractHttpConfigurer::disable);
        /*http.cors(Customizer.withDefaults());*/

        //se indica la clase que maneja excepciones
        http.exceptionHandling(exception -> exception.authenticationEntryPoint((request, response, authException) -> {
            log.error("[!] UNAUTHORIZED ERROR -> {}", authException.getMessage());
            response.sendError(401, authException.getMessage());
        }));

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //filtro de autorización de rutas
        http.authorizeHttpRequests(authorize -> {
            //todas estas rutas se permiten (no requieren autenticación)
            authorize.requestMatchers(
                            "/swagger-ui/**",
                            "/api/v1/auth/**",
                            "/v3/api-docs/**",
                            "/error",
                            "/favicon.ico",
                              "/api/v1/**"
                    ).permitAll();

            //todas las demás rutas requieren autenticación
            authorize.anyRequest().authenticated();
        });

        //agrega el filtro de autenticación basado en token personalizado
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
