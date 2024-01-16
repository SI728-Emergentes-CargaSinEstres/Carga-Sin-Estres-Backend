package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Review.response;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * The ReviewResponseDto class represents the data transfer object of the Review class.
 * It contains fields related to the details of a review entity.
 * This class is used for review information when retrieving a review.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private int rating;
    private String comment;
}
