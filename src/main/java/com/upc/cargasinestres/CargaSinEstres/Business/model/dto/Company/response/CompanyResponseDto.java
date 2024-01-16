package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String photo;
    private String description;
    private String NumeroContacto;
    private String direccion;
    private boolean transporte;
    private boolean carga;
    private boolean embalaje;
    private boolean montaje;
    private boolean desmontaje;
    private int averageRating;
    private String userType;
}