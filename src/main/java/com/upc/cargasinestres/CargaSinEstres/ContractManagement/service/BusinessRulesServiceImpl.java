package com.upc.cargasinestres.CargaSinEstres.ContractManagement.service;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.AdditionalWaitingTime.request.AdditionalWaitingTimeRequestDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.AdditionalWaitingTime.response.AdditionalWaitingTimeResponseDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity.AdditionalWaitingTime;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.repository.IAdditionalWaitingTimeRepository;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.service.IBusinessRulesService;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BusinessRulesServiceImpl implements IBusinessRulesService {
    private final ModelMapper modelMapper;
    private final IAdditionalWaitingTimeRepository additionalWaitingTimeRepository;

    public BusinessRulesServiceImpl(ModelMapper modelMapper, IAdditionalWaitingTimeRepository additionalWaitingTimeRepository) {
        this.modelMapper = modelMapper;
        this.additionalWaitingTimeRepository = additionalWaitingTimeRepository;
    }

    @Override
    public AdditionalWaitingTimeResponseDto getAdditionalWaitingTimeByCustomerId(Long customerId) {
        var additionalWaitingTime = additionalWaitingTimeRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el tiempo de espera adicional para el cliente con id: " + customerId));
        return modelMapper.map(additionalWaitingTime, AdditionalWaitingTimeResponseDto.class);
    }

    @Override
    public AdditionalWaitingTimeResponseDto createAdditionalWaitingTime(AdditionalWaitingTimeRequestDto additionalWaitingTimeRequestDto) {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextReservationDate = currentDate.plusDays(3);

        var newAdditionalWaitingTime = modelMapper.map(additionalWaitingTimeRequestDto, AdditionalWaitingTime.class);
        newAdditionalWaitingTime.setNextReservationDate(nextReservationDate);
        newAdditionalWaitingTime.setReason(additionalWaitingTimeRequestDto.getReason());
        newAdditionalWaitingTime.setCustomerId(additionalWaitingTimeRequestDto.getCustomerId());

        var createdAdditionalWaitingTime = additionalWaitingTimeRepository.save(newAdditionalWaitingTime);
        return modelMapper.map(createdAdditionalWaitingTime, AdditionalWaitingTimeResponseDto.class);
    }

    @Override
    public void deleteAdditionalWaitingTime() {
        LocalDate currentDate = LocalDate.now();

        List<AdditionalWaitingTime> expiredWaitingTimes = additionalWaitingTimeRepository
                .findByNextReservationDateLessThanEqual(currentDate);

        if (expiredWaitingTimes.isEmpty()) {
            System.out.println("No additional waiting times found to delete for date: " + currentDate);
            return;
        }

        additionalWaitingTimeRepository.deleteAll(expiredWaitingTimes);
        System.out.println(expiredWaitingTimes.size() + " additional waiting times deleted for date: " + currentDate);
    }
}
