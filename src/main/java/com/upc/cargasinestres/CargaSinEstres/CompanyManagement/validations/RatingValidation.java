package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.validations;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Rating.request.RatingRequestDto;

public class RatingValidation {

    public static void ValidateRating(RatingRequestDto ratingRequestDto) {
        if (ratingRequestDto.getStars() < 1 || ratingRequestDto.getStars() > 5) {
            throw new IllegalArgumentException("La calificación de estrellas debe estar en el rango de 1 a 5");
        }
    }
}
