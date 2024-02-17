package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.response;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents the response of the chat
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponseDto {

    private List<Message> messages;

}
