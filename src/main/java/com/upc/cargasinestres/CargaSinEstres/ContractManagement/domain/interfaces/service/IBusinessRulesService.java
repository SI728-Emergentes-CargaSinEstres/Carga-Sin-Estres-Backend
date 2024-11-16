package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.service;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.AdditionalWaitingTime.request.AdditionalWaitingTimeRequestDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.AdditionalWaitingTime.response.AdditionalWaitingTimeResponseDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.CompanyServiceViolation.request.CompanyServiceViolationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.CompanyServiceViolation.response.CompanyServiceViolationResponseDto;

import java.time.LocalDate;

public interface IBusinessRulesService {
    //GET
    public abstract AdditionalWaitingTimeResponseDto getAdditionalWaitingTimeByCustomerId(Long customerId);
    public abstract int getCompanyServiceViolationCountByCompanyIdAndYear(Long companyId, int year);

    //CREATE
    public abstract AdditionalWaitingTimeResponseDto createAdditionalWaitingTime(AdditionalWaitingTimeRequestDto additionalWaitingTimeRequestDto);
    public abstract CompanyServiceViolationResponseDto createCompanyServiceViolation(CompanyServiceViolationRequestDto companyServiceViolationRequestDto);

    //DELETE
    public abstract void deleteAdditionalWaitingTime();
}
