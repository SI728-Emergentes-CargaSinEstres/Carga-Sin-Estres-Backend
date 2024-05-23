package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.CustomerValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.MembershipValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.request.MembershipRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.response.MembershipResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Membership;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IMembershipRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IMembershipService;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Implementation of the IMembershipService interface.
 * Handles the business logic for subscription operations.
 * @author Grupo1
 * @version 2.0*/
@Service
public class MembershipServiceImpl implements IMembershipService {

    private final IMembershipRepository membershipRepository;
    private final ICompanyRepository companyRepository;

    private final ModelMapper modelMapper;

    public MembershipServiceImpl(IMembershipRepository membershipRepository, ICompanyRepository companyRepository, ModelMapper modelMapper) {
        this.membershipRepository = membershipRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MembershipResponseDto createMembership(Long companyId, MembershipRequestDto membershipRequestDto) {
        MembershipValidation.ValidateMembership(membershipRequestDto);

        var membership = modelMapper.map(membershipRequestDto, Membership.class);

        Company company = companyRepository.findById(companyId)
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
