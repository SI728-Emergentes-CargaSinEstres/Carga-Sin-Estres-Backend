package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.Response;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    private Reservation reservation;

}
