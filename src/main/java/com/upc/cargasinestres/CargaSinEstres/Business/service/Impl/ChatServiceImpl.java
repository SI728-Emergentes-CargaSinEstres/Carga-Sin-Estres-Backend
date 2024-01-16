package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.request.ChatRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.response.ChatResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Chat;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IBookingHistoryRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IChatRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IChatService;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class implements the methods defined in the IChatService interface
 * @author Grupo1
 * @version 1.0
 */
@Service
public class ChatServiceImpl implements IChatService {

    private final IChatRepository chatRepository;
    private final ModelMapper modelMapper;
    private final IBookingHistoryRepository bookingHistoryRepository;

    public ChatServiceImpl(IChatRepository chatRepository, ModelMapper modelMapper, IBookingHistoryRepository bookingHistoryRepository){
        this.chatRepository = chatRepository;
        this.modelMapper = modelMapper;
        this.bookingHistoryRepository = bookingHistoryRepository;
    }

    @Override
    public ChatResponseDto createChat(Long bookingId, String userType, ChatRequestDto chatRequestDto){
        var booking = bookingHistoryRepository.findById(bookingId)
                .orElseThrow(()->new ResourceNotFoundException("No se encontró el historial de reserva con id " + bookingId));

        var newChat = modelMapper.map(chatRequestDto,Chat.class);

        newChat.setDateTime(LocalDateTime.now());

        var previousChats = chatRepository.findByBookingHistoryId(bookingId);
        if(previousChats == null)
            newChat.setMessageOrder(1);
        else
            newChat.setMessageOrder(previousChats.size()+1);

        newChat.setBookingHistory(booking);
        newChat.setUser(userType);
        var createdChat = chatRepository.save(newChat);

        return modelMapper.map(createdChat, ChatResponseDto.class);

    }

    @Override
    public List<ChatResponseDto> getChatsByBookingHistoryId(Long bookingHistoryId){
        var chats = chatRepository.findByBookingHistoryId(bookingHistoryId);

        return chats.stream()
                .map(chat -> modelMapper.map(chat, ChatResponseDto.class))
                .toList();
    }


}
