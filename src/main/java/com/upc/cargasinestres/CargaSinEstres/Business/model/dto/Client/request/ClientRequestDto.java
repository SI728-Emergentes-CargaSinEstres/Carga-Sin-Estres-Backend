package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The ClientRequestDto class represents the data transfer object of the Client class.
 * It contains fields related to the details of a client entity.
 * This class is used for client information when creating or updating a client.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {
    private String name;

    private String apellidoMaterno;

    private String apellidoPaterno;

    private String celular;

    private String direccion;

    private String email;

    private String password;

    //private String userType;
}
