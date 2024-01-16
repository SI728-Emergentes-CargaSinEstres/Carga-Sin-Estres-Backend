package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;

import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.request.CompanyRequestDto;

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
        if(companyRequestDto.getPhoto().isEmpty()){
            throw new ValidationException("La foto de la empresa no puede estar vacio");
        }
        if(companyRequestDto.getName().length()>30) {
            throw new ValidationException("El nombre de la empresa no puede exceder los 30 caracteres");
        }
        if(companyRequestDto.getDescription().length() > 250){
            throw new ValidationException("La descripcion de la empresa no puede exceder los 250 caracteres");
        }

        if(!companyRequestDto.isTransporte() && !companyRequestDto.isCarga() && !companyRequestDto.isEmbalaje()
                && !companyRequestDto.isMontaje() && !companyRequestDto.isDesmontaje()
        ){
            throw new ValidationException("Como empresa debe ofrecer al menos un servicio");
        }

    }

}
