package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.Contract.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponseDto {
    private Long id;
    private Long reservationId;
    private String hashCodeValue;
    private LocalDate registeredDate;
    private Time registeredTime;
}
