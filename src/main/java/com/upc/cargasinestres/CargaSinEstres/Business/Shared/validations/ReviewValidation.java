package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;

import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Review.request.ReviewRequestDto;

/**
 * The ReviewValidation class provides methods for validating ReviewRequestDto objects.
 * It checks for the presence and validity of fields in a review request.
 */
public class ReviewValidation {
    /**
     * Validates the provided ReviewRequestDto object.
     *
     * @param reviewRequestDto The ReviewRequestDto object to be validated.
     * @throws ValidationException if any validation rule is not met.
     */
    public static void ValidateReview(ReviewRequestDto reviewRequestDto){
        if(reviewRequestDto.getComment() == null || reviewRequestDto.getComment().trim().isEmpty()){
            reviewRequestDto.setComment("...");
        }

        if(reviewRequestDto.getRating() == 0 || reviewRequestDto.getRating() < 0 || reviewRequestDto.getRating() > 5){
            throw new ValidationException("El rating es obligatorio, y debe ser un valor entre 1 y 5");
        }
    }
}
