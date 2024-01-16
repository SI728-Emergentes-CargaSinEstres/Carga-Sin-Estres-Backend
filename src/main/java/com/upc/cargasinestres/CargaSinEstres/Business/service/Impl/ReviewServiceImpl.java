package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.ReviewValidation;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Review.request.ReviewRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Review.response.ReviewResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Review;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IReviewService;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IReviewRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

/**
 * Implementation of the IReviewService interface.
 * Handles the business logic for review operations.
 * @author Grupo1
 * @version 1.0
 * */
@Service
public class ReviewServiceImpl implements IReviewService {

    private final IReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final ICompanyRepository companyRepository;

    //inyeccion de dependencias
    public ReviewServiceImpl(IReviewRepository reviewRepository, ModelMapper modelMapper, ICompanyRepository companyRepository){
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<ReviewResponseDto> getReviewsByCompanyId(Long companyId){
        var reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().map(
                Review->modelMapper.map(Review, ReviewResponseDto.class)
        ).toList();
    }

    @Override
    public ReviewResponseDto createReview(Long companyId, ReviewRequestDto reviewRequestDto) {
        var company = companyRepository.findById(companyId)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro la empresa con id: "+companyId));

        ReviewValidation.ValidateReview(reviewRequestDto);
        var newReview = modelMapper.map(reviewRequestDto, Review.class);

        //update averageRating of company
        var reviews = reviewRepository.findByCompanyId(companyId);
        reviews.add(newReview);

        var ratingTotal = 0;
        for (Review review: reviews) {
           ratingTotal += review.getRating();
        }

        var ratingAverage = (int)Math.round(ratingTotal/reviews.size());
        company.setAverageRating(ratingAverage);
        companyRepository.save(company); //saved in the DB

        //save the review
        newReview.setCompany(company);
        var createdReview = reviewRepository.save(newReview);
        return modelMapper.map(createdReview, ReviewResponseDto.class);
    }

}
