package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.controller;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Rating.response.RatingResponseDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.service.IRatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Rating Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class RatingController {
    private final IRatingService ratingService;

    public RatingController(IRatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/{idCompany}/ratings")
    public ResponseEntity<RatingResponseDto> createRating(@PathVariable Long idCompany, @RequestBody RatingRequestDto ratingRequestDto){

        var res = ratingService.createRating(idCompany, ratingRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
