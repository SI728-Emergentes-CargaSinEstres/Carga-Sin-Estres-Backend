package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Review.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * The ReviewRequestDto class represents the data transfer object of the Review class.
 * It contains fields related to the details of a review entity.
 * This class is used for review information when creating a review.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private int rating;
    private String comment;
}
