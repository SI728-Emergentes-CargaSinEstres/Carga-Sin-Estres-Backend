package com.upc.cargasinestres.CargaSinEstres.Users.controller;

import com.upc.cargasinestres.CargaSinEstres.Shared.dto.response.ApiResponse;
import com.upc.cargasinestres.CargaSinEstres.Users.model.dto.UserResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Users.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * REST User controller for the management of users
 * Provides the methods to manage the users of the application
 * @author Grupo1
 * @version 1.0
 */
@Tag(name = "User")
@RequestMapping("/api/v1/user")
@RestController
public class UserController {
    private final IUserService userService;

    /**
     * Class constructor
     * @param userService the service that manages the users
     */
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * This method is used to get the profile of a user
     * @param userId the id of the user
     * @return the profile of the user
     */
    @Operation(summary = "Obtiene el perfil de un usuario")
    @GetMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDto>> profile(@PathVariable Long userId) {
        var res = userService.profile(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * This method is used to delete an user by id
     * @param userId the id of the user
     * @return the response of the operation
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Elimina un usuario (ADMIN)")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<Object>> deleteById(@PathVariable Long userId) {
        var res = userService.deleteById(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}