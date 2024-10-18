package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Timeblock;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
