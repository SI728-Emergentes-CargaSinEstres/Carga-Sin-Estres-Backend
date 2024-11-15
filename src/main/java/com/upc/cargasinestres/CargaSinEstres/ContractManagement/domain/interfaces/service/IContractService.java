package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.service;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.Contract.request.ContractRequestDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.Contract.response.ContractResponseDto;

import java.math.BigInteger;
import java.util.Optional;

public interface IContractService {

    public abstract ContractResponseDto createContract(ContractRequestDto contractRequestDto);
    public abstract ContractResponseDto getContractByReservationId(Long reservationId);
    public abstract String deployContract(BigInteger reservationId, BigInteger registeredDate, BigInteger registeredTime) throws Exception;

}
