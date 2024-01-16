package com.upc.cargasinestres.CargaSinEstres.Security.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *Class that represents the login request dto for the endpoint
 * @author Grupo1*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "El nombre de usuario o email es requerido")
    private String usernameOrEmail;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 3, message = "La contraseña debe tener por lo menos 3 caracteres")
    private String password;
}