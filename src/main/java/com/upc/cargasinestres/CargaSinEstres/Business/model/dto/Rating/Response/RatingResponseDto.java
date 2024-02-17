package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.Response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponseDto {

    private Long id;

    private int stars;

}
