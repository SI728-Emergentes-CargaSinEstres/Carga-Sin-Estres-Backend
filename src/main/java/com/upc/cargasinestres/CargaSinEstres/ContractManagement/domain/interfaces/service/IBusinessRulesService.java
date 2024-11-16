package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.service;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.AdditionalWaitingTime.request.AdditionalWaitingTimeRequestDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.AdditionalWaitingTime.response.AdditionalWaitingTimeResponseDto;

import java.time.LocalDate;

public interface IBusinessRulesService {
    //GET
    public abstract AdditionalWaitingTimeResponseDto getAdditionalWaitingTimeByCustomerId(Long customerId);

    //CREATE
    public abstract AdditionalWaitingTimeResponseDto createAdditionalWaitingTime(AdditionalWaitingTimeRequestDto additionalWaitingTimeRequestDto);

    //DELETE
    public abstract void deleteAdditionalWaitingTime();
}
