package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;


import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.request.CustomerRequestDto;
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
    public static void ValidateClient(CustomerRequestDto customerRequestDto){

        if(customerRequestDto.getName().length() > 20){
            throw new ValidationException("El nombre no puede exceder los 20 caracteres");
        }
        if(customerRequestDto.getLast_name().length() > 20){
            throw new ValidationException("El apellido no puede exceder los 20 caracteres");
        }
        if(customerRequestDto.getEmail() == null || customerRequestDto.getEmail().isEmpty()){
            throw new ValidationException("El email del cliente debe ser obligatorio"); //ERROR 400
        }

        if(customerRequestDto.getPassword() == null || customerRequestDto.getPassword().isEmpty()){
            throw new ValidationException("La contraseña del cliente debe ser obligatorio");
        }

        if(customerRequestDto.getName().isEmpty()){
            throw new ValidationException("El nombre del cliente debe ser obligatorio");
        }

        if(customerRequestDto.getLast_name().isEmpty()) {
            throw new ValidationException("El apellido del cliente debe ser obligatorio");
        }

        if(customerRequestDto.getDirection() == null || customerRequestDto.getDirection().isEmpty()){
            throw new ValidationException("La direccion del cliente debe ser obligatorio");
        }

        if(customerRequestDto.getPhone_number().length() > 9){
            throw new ValidationException("El celular del cliente no debe exceder los 9 caracteres");
        }

        if (!customerRequestDto.getPhone_number().matches("\\d+")) {
            throw new ValidationException("El celular debe contener solo dígitos enteros");
        }
    }
}
