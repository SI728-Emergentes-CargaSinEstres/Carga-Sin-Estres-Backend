package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.service.ISubscriptionService;
import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.SubscriptionValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Subscription.request.SubscriptionRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Subscription.response.SubscriptionResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Subscription;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ISubscriptionRepository;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Implementation of the ISubscriptionService interface.
 * Handles the business logic for subscription operations.
 * @author Grupo1
 * @version 1.0*/
@Service
public class SubscriptionServiceImpl implements ISubscriptionService {

    private final ISubscriptionRepository subscriptionRepository;

    private final ICompanyRepository companyRepository;

    private final ModelMapper modelMapper;

    public SubscriptionServiceImpl(ISubscriptionRepository subscriptionRepository, ICompanyRepository companyRepository, ModelMapper modelMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public SubscriptionResponseDto createSubscription(Long companyId, SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionValidation.ValidateSubscription(subscriptionRequestDto);

        var subscription = modelMapper.map(subscriptionRequestDto, Subscription.class);

        subscription.setCompany(companyRepository.findById(companyId)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro la empresa con id: "+companyId))); //Company of the subscription is set

        subscription.setSubscriptionDate(LocalDate.now());
        var createdSubscription = subscriptionRepository.save(subscription); //saved in the DB

        return modelMapper.map(createdSubscription, SubscriptionResponseDto.class);
    }

    @Override
    public SubscriptionResponseDto getSubscriptionByCompanyId(Long companyId){
        var subscription = subscriptionRepository.findByCompanyId(companyId)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro la suscripcion de la empresa con id: "+companyId));

        return modelMapper.map(subscription, SubscriptionResponseDto.class);
    }

}
