package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDto {

    private Long id;

    private String content;

    private LocalDateTime message_date;

    private Long reservationId;

}
