package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.service.IMembershipService;
import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.SubscriptionValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.request.MembershipRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.response.MembershipResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Membership;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IMembershipRepository;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ISubscriptionService interface.
 * Handles the business logic for subscription operations.
 * @author Grupo1
 * @version 2.0*/
@Service
public class MembershipServiceImpl implements IMembershipService {

    private final IMembershipRepository subscriptionRepository;

    private final ICompanyRepository companyRepository;

    private final ModelMapper modelMapper;

    public MembershipServiceImpl(IMembershipRepository subscriptionRepository, ICompanyRepository companyRepository, ModelMapper modelMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MembershipResponseDto createMembership(Long companyId, MembershipRequestDto membershipRequestDto) {
        SubscriptionValidation.ValidateSubscription(membershipRequestDto);

        var subscription = modelMapper.map(membershipRequestDto, Membership.class);

        subscription.setCompany(companyRepository.findById(companyId)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro la empresa con id: "+companyId))); //Company of the subscription is set

        var createdSubscription = subscriptionRepository.save(subscription); //saved in the DB

        return modelMapper.map(createdSubscription, MembershipResponseDto.class);
    }

    @Override
    public MembershipResponseDto getMembershipByCompanyId(Long companyId){
        var subscription = subscriptionRepository.findByCompanyId(companyId)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro la suscripcion de la empresa con id: "+companyId));

        return modelMapper.map(subscription, MembershipResponseDto.class);
    }

}
