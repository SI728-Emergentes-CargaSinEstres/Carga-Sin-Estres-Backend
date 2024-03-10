package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.request.CustomerRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;

import java.time.LocalDate;

/**
 * The CustomerValidation class provides methods for validating CustomerRequestDto objects.
 * It checks for the length and presence of required fields in a customer request.
 */
public class CustomerValidation {
    /**
     * Validates the provided CustomerRequestDto object.
     *
     * @param customerRequestDto The CustomerRequestDto object to be validated.
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
        if(customerRequestDto.getFirstName().isEmpty()){
            throw new ValidationException("El nombre del cliente debe ser obligatorio");
        }
        if(customerRequestDto.getLastName().isEmpty()) {
            throw new ValidationException("El apellido del cliente debe ser obligatorio");
        }
        if(customerRequestDto.getPhoneNumber().length() != 9){
            throw new ValidationException("El celular del cliente debe tener exactamente 9 digitos");
        }
        if (!customerRequestDto.getPhoneNumber().matches("\\d+")) {
            throw new ValidationException("El celular debe contener solo dígitos enteros");
        }
        if (customerRequestDto.getDateOfBirth() == null) {
            throw new ValidationException("La fecha de nacimiento del cliente debe ser obligatoria"); // ERROR 400
        }

        LocalDate currentDate = LocalDate.now(); // Calcular la fecha actual
        if (customerRequestDto.getDateOfBirth().isAfter(currentDate) ) {
            throw new ValidationException("La fecha de nacimiento no puede ser en el futuro"); // ERROR 400
        }

        LocalDate eighteenYearsAgo = currentDate.minusYears(17); // Calcular la fecha hace 18 años (validacion con un año menos)
        if (customerRequestDto.getDateOfBirth().isAfter(eighteenYearsAgo)) { // Comprobar si la fecha de nacimiento es posterior a la fecha hace 18 años
            throw new RuntimeException("No puede crear una cuenta si es menor de edad");
        }

        // Password Validation
        if (customerRequestDto.getPassword().length() < 8 ) {
            throw new ValidationException("La contraseña debe tener al menos 8 caracteres");
        }
        if (!customerRequestDto.getPassword().matches(".*[A-Z].*")) {
            throw new ValidationException("La contraseña debe contener al menos una letra mayúscula");
        }
        if (!customerRequestDto.getPassword().matches(".*[a-z].*")) {
            throw new ValidationException("La contraseña debe contener al menos una letra minúscula");
        }
        if (!customerRequestDto.getPassword().matches(".*\\d.*")) {
            throw new ValidationException("La contraseña debe contener al menos un número");
        }
    }
}