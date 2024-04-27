package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Rating.response.RatingResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.RatingValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Rating;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IRatingRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IRatingService;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Console;

@Service
@Qualifier("ratingServiceImpl")
public class RatingServiceImpl implements IRatingService {

    private final IRatingRepository ratingRepository;
    private final ICompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public RatingServiceImpl(IRatingRepository ratingRepository, ICompanyRepository companyRepository, ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RatingResponseDto createRating(Long companyId, RatingRequestDto ratingRequestDto) {
        var newRating = modelMapper.map(ratingRequestDto, Rating.class);
        var company = companyRepository.findCompanyById(companyId);

        newRating.setCompany(company);
        RatingValidation.ValidateRating(ratingRequestDto);

        var createdRating = ratingRepository.save(newRating);

        // Agregar el nuevo rating a la lista de ratings en la entidad Company
        company.getRatings().add(createdRating);
        companyRepository.save(company);

        return modelMapper.map(createdRating, RatingResponseDto.class);
    }

}
