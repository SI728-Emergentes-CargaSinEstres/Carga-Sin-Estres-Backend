package com.upc.cargasinestres.CargaSinEstres.Security.model.dto.response;

import com.upc.cargasinestres.CargaSinEstres.Users.model.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents the API response when a user is registered
 * @author Grupo1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUserResponseDto {
    private Long userId;
    private String fullName;
    private String username;
    private String email;
    private Set<Role> roles = new HashSet<>();
}