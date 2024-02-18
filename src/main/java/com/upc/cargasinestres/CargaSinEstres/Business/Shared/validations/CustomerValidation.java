package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.request.CustomerRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;

/**
 * The ClientValidation class provides methods for validating ClientRequestDto objects.
 * It checks for the length and presence of required fields in a client request.
 */
public class CustomerValidation {
    /**
     * Validates the provided ClientRequestDto object.
     *
     * @param customerRequestDto The ClientRequestDto object to be validated.
     * @throws ValidationException if any validation rule is not met.
     */
    public static void ValidateCustomer(CustomerRequestDto customerRequestDto){

        if(customerRequestDto.getFirstName().length() > 20){
            throw new ValidationException("El nombre no puede exceder los 20 caracteres");
        }
        if(customerRequestDto.getLastName().length() > 20){
            throw new ValidationException("El apellido no puede exceder los 20 caracteres");
        }
        if(customerRequestDto.getEmail() == null || customerRequestDto.getEmail().isEmpty()){
            throw new ValidationException("El email del cliente debe ser obligatorio"); //ERROR 400
        }

        if(customerRequestDto.getPassword() == null || customerRequestDto.getPassword().isEmpty()){
            throw new ValidationException("La contraseña del cliente debe ser obligatorio");
        }

        if(customerRequestDto.getFirstName().isEmpty()){
            throw new ValidationException("El nombre del cliente debe ser obligatorio");
        }

        if(customerRequestDto.getLastName().isEmpty()) {
            throw new ValidationException("El apellido del cliente debe ser obligatorio");
        }

        if(customerRequestDto.getDirection() == null || customerRequestDto.getDirection().isEmpty()){
            throw new ValidationException("La direccion del cliente debe ser obligatorio");
        }

        if(customerRequestDto.getPhoneNumber().length() > 9){
            throw new ValidationException("El celular del cliente no debe exceder los 9 caracteres");
        }

        if (!customerRequestDto.getPhoneNumber().matches("\\d+")) {
            throw new ValidationException("El celular debe contener solo dígitos enteros");
        }
    }
}