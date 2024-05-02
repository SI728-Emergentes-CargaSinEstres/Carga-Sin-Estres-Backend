package com.upc.cargasinestres.CargaSinEstres;

import com.upc.cargasinestres.CargaSinEstres.Business.controller.CustomerController;
import com.upc.cargasinestres.CargaSinEstres.Business.controller.RatingController;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.response.RatingResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.ICustomerService;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IRatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingControllerTest {

    private RatingController ratingController;
    private IRatingService ratingService;

    @BeforeEach
    public void setup() {
        // Arrange
        ratingService = new RatingControllerTest.MockRatingService();
        ratingController = new RatingController(ratingService);
    }

    @Test
    public void testCreateRating() throws ParseException{
        //Arrange
        Long companyId = 1L;
        RatingRequestDto requestDto = new RatingRequestDto(5);

        // Act
        ResponseEntity<RatingResponseDto> response = ratingController.createRating(companyId, requestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        RatingResponseDto createdRating = response.getBody();
        assertEquals(5, createdRating.getStars());

    }

    private static class MockRatingService implements IRatingService {

        @Override
        public RatingResponseDto createRating(Long idCompany, RatingRequestDto rating) {
            return new RatingResponseDto(rating.getStars());
        }
    }
}
