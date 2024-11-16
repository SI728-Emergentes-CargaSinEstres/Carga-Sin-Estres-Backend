package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.CompanyServiceViolation.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyServiceViolationRequestDto {
    private Long companyId;
    private String reason;
}
