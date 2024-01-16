package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The ChatRequestDto class represents the data transfer object of the Chat class.
 * It contains fields related to the details of a chat entity.
 * This class is used for the communication between the client and the company.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestDto {
//    private String user;
    private String message;
}
