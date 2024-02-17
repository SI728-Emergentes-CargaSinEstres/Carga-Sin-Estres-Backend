package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.response.RatingResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Rating;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IRatingRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IRatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ratingServiceImpl")
public class RatingServiceImpl implements IRatingService {

    private final IRatingRepository ratingRepository;
    private final ICompanyRepository companyRepository;

    private final ModelMapper modelMapper;

    public RatingServiceImpl(IRatingRepository ratingRepository, ICompanyRepository companyRepository ,ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RatingResponseDto createRating(Long companyId, RatingRequestDto ratingRequestDto) {
        var newRating = modelMapper.map(ratingRequestDto, Rating.class);

        var company = companyRepository.findCompanyById(companyId);

        newRating.setCompany(company);

        var createdRating = ratingRepository.save(newRating);

        return modelMapper.map(createdRating, RatingResponseDto.class);
    }
}
