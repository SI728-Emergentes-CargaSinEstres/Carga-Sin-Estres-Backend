package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.interfaces.service;


import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.dto.Rating.response.RatingResponseDto;

public interface IRatingService {
    //create rating
    public abstract RatingResponseDto createRating(Long idCompany, RatingRequestDto rating);


}
