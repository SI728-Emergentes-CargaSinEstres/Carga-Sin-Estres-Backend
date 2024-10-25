package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Rating.request.RatingRequestDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Rating.response.RatingResponseDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.validations.RatingValidation;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.entity.Rating;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.repository.IRatingRepository;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.service.IRatingService;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.shared.UserQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ratingServiceImpl")
public class RatingServiceImpl implements IRatingService {

    private final IRatingRepository ratingRepository;
    private final ModelMapper modelMapper;
    private final UserQueryService userQueryService;

    public RatingServiceImpl(IRatingRepository ratingRepository, ModelMapper modelMapper, UserQueryService userQueryService) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
        this.userQueryService = userQueryService;
    }

    @Override
    public RatingResponseDto createRating(Long companyId, RatingRequestDto ratingRequestDto) {
        var newRating = modelMapper.map(ratingRequestDto, Rating.class);
        var company = userQueryService.existsByCompanyId(companyId);

        newRating.setCompany(company.get());
        RatingValidation.ValidateRating(ratingRequestDto);

        var createdRating = ratingRepository.save(newRating);

        // Agregar el nuevo rating a la lista de ratings en la entidad Company
        company.get().getRatings().add(createdRating);
        userQueryService.saveCompany(company.get());

        return modelMapper.map(createdRating, RatingResponseDto.class);
    }

}
