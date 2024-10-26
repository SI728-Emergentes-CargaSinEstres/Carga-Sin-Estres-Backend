package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.service;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.application.validation.MembershipValidation;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.dto.Membership.request.MembershipRequestDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.dto.Membership.response.MembershipResponseDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.entity.Membership;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.interfaces.repository.IMembershipRepository;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.interfaces.service.IMembershipService;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.shared.UserQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Implementation of the IMembershipService interface.
 * Handles the business logic for subscription operations.
 * @author Grupo1
 * @version 2.0*/
@Service
public class MembershipServiceImpl implements IMembershipService {

    private final IMembershipRepository membershipRepository;

    private final UserQueryService userQueryService;

    private final ModelMapper modelMapper;

    public MembershipServiceImpl(IMembershipRepository membershipRepository, UserQueryService userQueryService, ModelMapper modelMapper) {
        this.membershipRepository = membershipRepository;
        this.userQueryService = userQueryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public MembershipResponseDto createMembership(Long companyId, MembershipRequestDto membershipRequestDto) {
        MembershipValidation.ValidateMembership(membershipRequestDto);

        var membership = modelMapper.map(membershipRequestDto, Membership.class);

        var company = userQueryService.existsByCompanyId(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la empresa con ID: " + companyId));

        membership.setCompany(company);

        var createdMembership = membershipRepository.save(membership);

        return modelMapper.map(createdMembership, MembershipResponseDto.class);
    }


    @Override
    public MembershipResponseDto getMembershipByCompanyId(Long companyId){
        var subscription = membershipRepository.findByCompanyId(companyId);
        if (subscription == null) {
            throw new ResourceNotFoundException("No se encontro la membresía de la empresa con id: " + companyId);
        };

        return modelMapper.map(subscription, MembershipResponseDto.class);
    }

}
