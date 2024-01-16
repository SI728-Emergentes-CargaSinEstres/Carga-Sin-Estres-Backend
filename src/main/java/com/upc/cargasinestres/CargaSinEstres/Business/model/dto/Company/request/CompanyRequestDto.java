package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String photo;
    private String description;
    private String email;
    private String NumeroContacto;
    private String direccion;
    private String password;
    //private String userType;
    private boolean transporte;
    private boolean carga;
    private boolean embalaje;
    private boolean montaje;
    private boolean desmontaje;
}
