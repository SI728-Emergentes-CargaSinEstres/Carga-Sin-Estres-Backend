package com.upc.cargasinestres.CargaSinEstres.Users.model.dto;

import com.upc.cargasinestres.CargaSinEstres.Users.model.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * The UserResponseDto class represents the data transfer object for creating a user.
 * It contains fields related to the details of a user.
 * The fields of the UserResponseDto class are:
 *  - userId: the id of the user.
 *  - fullName: the full name of the user.
 *  - username: the username of the user.
 *  - email: the email of the user.
 *  - roles: the roles of the user (user or admin).
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String fullName;
    private String username;
    private String email;
    private Set<Role> roles = new HashSet<>();
}