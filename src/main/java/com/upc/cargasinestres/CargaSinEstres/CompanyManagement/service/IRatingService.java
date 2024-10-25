package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.service;


import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Rating.response.RatingResponseDto;

public interface IRatingService {
    //create rating
    public abstract RatingResponseDto createRating(Long idCompany, RatingRequestDto rating);


}
