package com.upc.cargasinestres.CargaSinEstres.Security.service;

import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.request.LoginRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.request.RegisterRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.response.RegisteredUserResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.response.TokenResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Shared.dto.response.ApiResponse;

/**
 * Interface for the service that defines methods for user authentication and registration.
 * @author Grupo1*/
public interface IAuthService {
    /**
     * Registra un usuario
     * @param request Datos para el registro
     * @return Usuario registrado
     */
    ApiResponse<RegisteredUserResponseDto> registerUser(RegisterRequestDto request);

    /**
     * Realiza el login del usuario
     * @param request Credenciales
     * @return Token de acceso
     */
    ApiResponse<TokenResponseDto> login(LoginRequestDto request);
}