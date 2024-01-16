package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;


import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.request.ClientRequestDto;
/**
 * The ClientValidation class provides methods for validating ClientRequestDto objects.
 * It checks for the length and presence of required fields in a client request.
 */
public class ClientValidation {

    /**
     * Validates the provided ClientRequestDto object.
     *
     * @param clientRequestDto The ClientRequestDto object to be validated.
     * @throws ValidationException if any validation rule is not met.
     */
    public static void ValidateClient(ClientRequestDto clientRequestDto){

        if(clientRequestDto.getName().length() > 20){
            throw new ValidationException("El nombre no puede exceder los 20 caracteres");
        }
        if(clientRequestDto.getApellidoMaterno().length() > 20){
            throw new ValidationException("El apellido materno no puede exceder los 20 caracteres");
        }
        if(clientRequestDto.getApellidoPaterno().length() > 20){
            throw new ValidationException("El apellido paterno no puede exceder los 20 caracteres");
        }
        if(clientRequestDto.getEmail() == null || clientRequestDto.getEmail().isEmpty()){
            throw new ValidationException("El email del cliente debe ser obligatorio"); //ERROR 400
        }

        if(clientRequestDto.getPassword() == null || clientRequestDto.getPassword().isEmpty()){
            throw new ValidationException("La contraseña del cliente debe ser obligatorio");
        }

        if(clientRequestDto.getName().isEmpty()){
            throw new ValidationException("El nombre del cliente debe ser obligatorio");
        }

        if(clientRequestDto.getApellidoMaterno().isEmpty()){
            throw new ValidationException("El apellido del cliente debe ser obligatorio");
        }

        if(clientRequestDto.getApellidoPaterno().isEmpty()){
            throw new ValidationException("El apellido del cliente debe ser obligatorio");
        }

        if(clientRequestDto.getDireccion() == null || clientRequestDto.getDireccion().isEmpty()){
            throw new ValidationException("La direccion del cliente debe ser obligatorio");
        }

        if(clientRequestDto.getCelular().length() > 9){
            throw new ValidationException("El celular del cliente no debe exceder los 9 caracteres");
        }

        if (!clientRequestDto.getCelular().matches("\\d+")) {
            throw new ValidationException("El celular debe contener solo dígitos enteros");
        }

    }
}
