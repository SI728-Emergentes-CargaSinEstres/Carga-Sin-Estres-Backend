package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Review.request.ReviewRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Review.response.ReviewResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IReviewService;

import java.util.List;

/**
 * REST Review controller
 * @author Grupo1
 * @version 1.0
 */
@Tag(name="Review Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ReviewController {
    private final IReviewService reviewService;

    /**
     * Class constructor
     *
     * @param reviewService The service for handling review operations.
     */
    public ReviewController(IReviewService reviewService){
        this.reviewService = reviewService;
    }

    /**
     * Retrieves a list of reviews based on the company ID.
     *
     * @param companyId The ID of the company.
     * @return A ResponseEntity containing the list of ReviewResponseDto and HttpStatus.OK.
     */
    @Operation(summary = "Get all reviews by company Id")
    @GetMapping("/reviews/{companyId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByCompanyId(@PathVariable(name="companyId") Long companyId){
        var res = reviewService.getReviewsByCompanyId(companyId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Creates a new review for the specified company.
     *
     * @param companyId The ID of the company for which the review is created.
     * @param reviewRequestDto The data for creating the review.
     * @return A ResponseEntity containing the created ReviewResponseDto and HttpStatus.CREATED.
     */
    @Operation(summary = "Create a Review")
    @PostMapping("/reviews/{companyId}")
    public ResponseEntity<ReviewResponseDto> createReview(@PathVariable(name = "companyId") Long companyId, @RequestBody ReviewRequestDto reviewRequestDto){
        var res = reviewService.createReview(companyId, reviewRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
