package com.upc.cargasinestres.CargaSinEstres.Business.service;


import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Review.request.ReviewRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Review.response.ReviewResponseDto;

import java.util.List;

/**
 * The IReviewService interface defines methods for managing reviews.
 * Implementing classes should provide functionality for creating and retrieving reviews.
 */
public interface IReviewService {

    /**
     * Creates a new review for the specified company.
     *
     * @param companyId The ID of the company for which the review is created.
     * @param review The data for creating the review.
     * @return A ReviewResponseDto representing the created review.
     */
    public abstract ReviewResponseDto createReview(Long companyId, ReviewRequestDto review);

    /**
     * Retrieves a list of reviews based on the company ID.
     *
     * @param companyId The ID of the company.
     * @return A List of ReviewResponseDto representing reviews associated with the specified company.
     */
    public abstract List<ReviewResponseDto> getReviewsByCompanyId(Long companyId);
}
