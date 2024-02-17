package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.Request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.Response.RatingResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Rating;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IRatingRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IRatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ratingServiceImpl")
public class RatingServiceImpl implements IRatingService {

    private final IRatingRepository ratingRepository;

    private final ModelMapper modelMapper;

    public RatingServiceImpl(IRatingRepository ratingRepository, ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RatingResponseDto createRating(RatingRequestDto ratingRequestDto) {
        var newRating = modelMapper.map(ratingRequestDto, Rating.class);

        var createdRating = ratingRepository.save(newRating);

        return modelMapper.map(createdRating, RatingResponseDto.class);
    }
}
