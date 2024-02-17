package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.request.ChatRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.response.ChatResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Chat;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IReservationRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IChatRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IChatService;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * This class implements the methods defined in the IChatService interface
 * @author Grupo1
 * @version 1.0
 */
@Service
public class ChatServiceImpl implements IChatService {

    private final IChatRepository chatRepository;
    private final ModelMapper modelMapper;
    private final IReservationRepository reservationRepository;

    public ChatServiceImpl(IChatRepository chatRepository, ModelMapper modelMapper, IReservationRepository reservationRepository){
        this.chatRepository = chatRepository;
        this.modelMapper = modelMapper;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ChatResponseDto createChat(Long reservationId, ChatRequestDto chatRequestDto){
        /*
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(()->new ResourceNotFoundException("No se encontró el historial de reserva con id " + reservationId));
            */

        var newChat = new Chat(); //revision

        /*
        newChat.setReservation(reservation);
         */
        var createdChat = chatRepository.save(newChat);

        return modelMapper.map(createdChat, ChatResponseDto.class);
    }


}
