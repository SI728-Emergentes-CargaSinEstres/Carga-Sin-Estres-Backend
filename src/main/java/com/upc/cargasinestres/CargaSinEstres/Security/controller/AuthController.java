package com.upc.cargasinestres.CargaSinEstres.Security.controller;

import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.request.LoginRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.response.RegisteredUserResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.request.RegisterRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.response.TokenResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Security.service.AuthService;
import com.upc.cargasinestres.CargaSinEstres.Security.service.IAuthService;
import com.upc.cargasinestres.CargaSinEstres.Shared.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for authentication and user registration
 * @author Grupo1
 */
@Tag(name = "Auth")
@SecurityRequirements //desactiva la seguridad para este controlador (swagger)
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    private final IAuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    /**
     * Log in
     * @param request Data for login
     * @return Access token
     */
    @Operation(summary = "Inicia sesión")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponseDto>> login(@Valid @RequestBody LoginRequestDto request) {
        var res = service.login(request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Registers a new user
     * @param request Data for the registration
     * @return Registered user
     */
    @Operation(summary = "Registra un nuevo usuario")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisteredUserResponseDto>> registerUser(@Valid @RequestBody RegisterRequestDto request) {
        var res = service.registerUser(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
