package com.upc.cargasinestres.CargaSinEstres.Business.service;


import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.response.RatingResponseDto;

public interface IRatingService {
    //create rating
    public abstract RatingResponseDto createRating(Long idCompany, RatingRequestDto rating);


}
