package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.AdditionalWaitingTime.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalWaitingTimeResponseDto {
    private Long customerId;
    private LocalDate nextReservationDate;
    private String reason;
}
