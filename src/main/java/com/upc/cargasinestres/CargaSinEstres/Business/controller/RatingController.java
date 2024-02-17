package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.response.RatingResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.ICompanyService;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IRatingService;
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
    private final ICompanyService companyService;

    public RatingController(IRatingService ratingService, ICompanyService companyService) {
        this.ratingService = ratingService;
        this.companyService = companyService;
    }

    @PostMapping("/ratings/{idCompany}")
    public ResponseEntity<RatingResponseDto> createRating(@PathVariable Long idCompany, @RequestBody RatingRequestDto ratingRequestDto){
        var company = companyService.getCompanyById(idCompany);

        var res = ratingService.createRating(idCompany, ratingRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
