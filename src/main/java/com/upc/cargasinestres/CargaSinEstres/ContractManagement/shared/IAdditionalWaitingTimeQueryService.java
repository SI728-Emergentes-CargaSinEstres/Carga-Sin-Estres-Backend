package com.upc.cargasinestres.CargaSinEstres.ContractManagement.shared;

public interface IAdditionalWaitingTimeQueryService {
    boolean existsByCustomerId(Long customerId);
}
