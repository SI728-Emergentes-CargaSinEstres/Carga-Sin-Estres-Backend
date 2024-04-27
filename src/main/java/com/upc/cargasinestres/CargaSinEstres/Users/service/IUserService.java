package com.upc.cargasinestres.CargaSinEstres.Users.service;

import com.upc.cargasinestres.CargaSinEstres.Shared.dto.response.ApiResponse;
import com.upc.cargasinestres.CargaSinEstres.Users.model.dto.UserResponseDto;

/**
 * Interface of the User Service class that contains the methods to be implemented
 * @author Grupo1
 */
public interface IUserService {
    /**
     * Obtains the data of the users by it's Id
     * @param userId id of the user
     * @return Data of the user
     */
    ApiResponse<UserResponseDto> profile(Long userId);

    /**
     * Deletes a User by it's Id
     * @param userId id of the user
     * @return Response of the operation
     */
    ApiResponse<Object> deleteById(Long userId);
}