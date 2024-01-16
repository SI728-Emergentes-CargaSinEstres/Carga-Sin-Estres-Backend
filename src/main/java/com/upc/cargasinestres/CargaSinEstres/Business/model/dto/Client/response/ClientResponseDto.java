package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The ClientResponseDto class represents the data transfer object of the Client class.
 * It contains fields related to the details of a client entity.
 * This class is used for client information when retrieving a client.
 * @version 1.0
 * @author Grupo1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {
    private Long id;
    private String name;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String celular;
    private String direccion;
}
