package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Service.Request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseDto {

    private Long id;

    private String name;

}
