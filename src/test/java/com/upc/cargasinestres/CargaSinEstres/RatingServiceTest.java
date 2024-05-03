package com.upc.cargasinestres.CargaSinEstres;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.response.RatingResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IRatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RatingServiceTest {

    private IRatingService ratingService;

    @BeforeEach
    public void setup() {
        // Arrange
        ratingService = new MockRatingService();
    }

    @DisplayName("Test to create a valid rating")
    @Test
    public void testCreateRating_ValidRating() {
        // Arrange
        Long companyId = 1L;
        RatingRequestDto requestDto = new RatingRequestDto(4);

        // Act
        RatingResponseDto createdRating = ratingService.createRating(companyId, requestDto);

        // Assert
        assertEquals(4, createdRating.getStars());
        assertEquals(companyId, createdRating.getCompanyId());
    }

    @DisplayName("Test to create a rating with an out of range rating")
    @Test
    public void testCreateRating_OutOfRangeRating() {
        // Arrange
        Long companyId = 1L;
        RatingRequestDto requestDto = new RatingRequestDto(6);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ratingService.createRating(companyId, requestDto));
    }

    @DisplayName("Test to create a rating with the lowest rating")
    @Test
    public void testCreateRating_LowestRating() {
        // Arrange
        Long companyId = 1L;
        RatingRequestDto requestDto = new RatingRequestDto(1);
        // Act
        RatingResponseDto createdRating = ratingService.createRating(companyId, requestDto);

        // Assert
        assertEquals(1, createdRating.getStars());
        assertEquals(companyId, createdRating.getCompanyId());
    }

    @DisplayName("Test to create a rating with the highest rating")
    @Test
    public void testCreateRating_HighestRating() {
        // Arrange
        Long companyId = 1L;
        RatingRequestDto requestDto = new RatingRequestDto(5);

        // Act
        RatingResponseDto createdRating = ratingService.createRating(companyId, requestDto);

        // Assert
        assertEquals(5, createdRating.getStars());
        assertEquals(companyId, createdRating.getCompanyId());
    }

    private static class MockRatingService implements IRatingService {

        @Override
        public RatingResponseDto createRating(Long idCompany, RatingRequestDto rating) {
            if (rating.getStars() < 1 || rating.getStars() > 5) {
                throw new IllegalArgumentException("La calificación debe estar entre 1 y 5 estrellas");
            }
            return new RatingResponseDto(rating.getStars(), idCompany);
        }
    }
}
