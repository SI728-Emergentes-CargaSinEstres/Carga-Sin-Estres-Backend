package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.request.CompanyRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;

/**
 * The CompanyValidation class provides methods for validating CompanyRequestDto objects.
 * It checks for the length and presence of required fields in a company request.
 */
public class CompanyValidation {

    /**
     * Validates the provided CompanyRequestDto object.
     *
     * @param companyRequestDto The CompanyRequestDto object to be validated.
     * @throws ValidationException if any validation rule is not met.
     */
    public static void ValidateCompany(CompanyRequestDto companyRequestDto){

        if(companyRequestDto.getName().isEmpty()){
            throw new ValidationException("El nombre de la empresa no puede estar vacio");
        }
        if(companyRequestDto.getLogo().isEmpty()){
            throw new ValidationException("El logo de la empresa no puede estar vacio");
        }
        if(!companyRequestDto.getLogo().toLowerCase().startsWith("https://")){
            throw new ValidationException("El logo de la empresa debe ser un url");
        }
        if(companyRequestDto.getName().length()>30) {
            throw new ValidationException("El nombre de la empresa no puede exceder los 30 caracteres");
        }
        if(companyRequestDto.getDescription().length() > 250){
            throw new ValidationException("La descripcion de la empresa no puede exceder los 250 caracteres");
        }
        if(companyRequestDto.getTIC().length() != 11) {
            throw new ValidationException("El RUC de la empresa debe tener exactamente 11 caracteres");
        }
        if(companyRequestDto.getPhoneNumber().length()!=9) {
            throw new ValidationException("El telefono de la empresa debe tener exactamente 9 digitos");
        }
        if (!companyRequestDto.getTIC().matches("\\d+")) {
            throw new ValidationException("El RUC de la empresa debe ser un número");
        }
        if (!companyRequestDto.getPhoneNumber().matches("\\d+")) {
            throw new ValidationException("El número de teléfono de la empresa debe ser un número");
        }


        // Password Validation
        if (companyRequestDto.getPassword().length() < 8 ) {
            throw new ValidationException("La contraseña debe tener al menos 8 caracteres");
        }
        if (!companyRequestDto.getPassword().matches(".*[A-Z].*")) {
            throw new ValidationException("La contraseña debe contener al menos una letra mayúscula");
        }
        if (!companyRequestDto.getPassword().matches(".*[a-z].*")) {
            throw new ValidationException("La contraseña debe contener al menos una letra minúscula");
        }
        if (!companyRequestDto.getPassword().matches(".*\\d.*")) {
            throw new ValidationException("La contraseña debe contener al menos un número");
        }
        if (!companyRequestDto.getPassword().matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?].*")) {
            throw new ValidationException("La contraseña debe contener al menos un carácter especial");
        }

        // Services validation
        if (companyRequestDto.getServicioIds().isEmpty()) {
            throw new ValidationException("La empresa debe ofrecer al menos un servicio");
        }

        long distinctServiceCount = companyRequestDto.getServicioIds().stream().distinct().count();
        if (distinctServiceCount != companyRequestDto.getServicioIds().size()) {
            throw new ValidationException("La empresa no puede ofrecer servicios duplicados");
        }
    }

}
