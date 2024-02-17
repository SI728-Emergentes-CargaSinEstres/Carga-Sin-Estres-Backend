package com.upc.cargasinestres.CargaSinEstres.Business.service;


import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.Request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.Response.RatingResponseDto;

public interface IRatingService {
    //create rating
    public abstract RatingResponseDto createRating(RatingRequestDto rating);


}
