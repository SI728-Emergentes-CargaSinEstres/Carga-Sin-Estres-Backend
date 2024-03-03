package com.upc.cargasinestres.CargaSinEstres.Business.service;


import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.request.MessageRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.response.MessageResponseDto;

public interface IMessageService {

    /**
     * Create a new Message with the information provided as a parameter
     * @param chatId The booking id
     * @return The created message information
     */
    public abstract MessageResponseDto createMessage(Long chatId, MessageRequestDto messageRequestDto);
}
