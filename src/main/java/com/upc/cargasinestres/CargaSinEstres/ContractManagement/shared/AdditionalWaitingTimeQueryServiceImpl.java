package com.upc.cargasinestres.CargaSinEstres.ContractManagement.shared;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.repository.IAdditionalWaitingTimeRepository;
import org.springframework.stereotype.Service;

@Service
public class AdditionalWaitingTimeQueryServiceImpl implements IAdditionalWaitingTimeQueryService{
    private final IAdditionalWaitingTimeRepository additionalWaitingTimeRepository;

    public AdditionalWaitingTimeQueryServiceImpl(IAdditionalWaitingTimeRepository additionalWaitingTimeRepository) {
        this.additionalWaitingTimeRepository = additionalWaitingTimeRepository;
    }

    @Override
    public boolean existsByCustomerId(Long customerId) {
        return additionalWaitingTimeRepository.existsByCustomerId(customerId);
    }
}
