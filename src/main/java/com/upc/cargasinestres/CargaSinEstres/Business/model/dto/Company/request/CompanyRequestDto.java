package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The CompanyRequestDto class represents the data transfer object of the Company class.
 * It contains fields related to the details of a company entity.
 * This class is used for company information when creating a company.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDto {
    private String name;
    private String TIC;
    private String direction;
    private String email;
    private String phoneNumber;
    private String password;
    private String description;
    private String logo;
    private List<Long> servicioIds;
}
