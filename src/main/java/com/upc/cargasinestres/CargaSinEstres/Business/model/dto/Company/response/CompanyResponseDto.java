package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.response;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.response.ServicioResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The CompanyResponseDto class represents the data transfer object of the Company class.
 * It contains fields related to the details of a company entity.
 * This class is used for company information when retrieving a company.
 * @author Grupo1
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDto {
    private Long id;
    private String name;
    private String TIC;
    private String direction;
    private String email;
    private String phoneNumber;
    private String description;
    private String logo;
    private List<ServicioResponseDto> servicios;
    private int averageRating;

}