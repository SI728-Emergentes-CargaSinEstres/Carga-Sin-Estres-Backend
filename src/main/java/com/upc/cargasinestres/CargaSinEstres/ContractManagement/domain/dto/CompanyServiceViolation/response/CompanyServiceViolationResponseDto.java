package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.CompanyServiceViolation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyServiceViolationResponseDto {
    private Long id;
    private Long companyId;
    private String reason;
    private LocalDate violationDate;
}
