package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.response;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.BookingHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class represents the response of the chat
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponseDto {

//    private Long id;
    private String user;
    private String message;
    private int messageOrder;
    private LocalDateTime dateTime;
    //private BookingHistory bookingHistory;

}
