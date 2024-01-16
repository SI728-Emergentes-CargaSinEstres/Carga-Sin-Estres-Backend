package com.upc.cargasinestres.CargaSinEstres.Security.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents the API response when a token is generated
 * @author Grupo1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private String token;
}