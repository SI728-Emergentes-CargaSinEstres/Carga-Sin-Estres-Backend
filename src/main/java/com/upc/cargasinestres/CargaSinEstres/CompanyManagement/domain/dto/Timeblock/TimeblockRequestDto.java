package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.dto.Timeblock;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeblockRequestDto {

    private Long companyId;
    private String startTime;
    private String endTime;
}
