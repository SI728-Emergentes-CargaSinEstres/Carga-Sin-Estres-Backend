package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.AdditionalWaitingTime.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalWaitingTimeRequestDto {
    private Long customerId;
    private String reason;
}
