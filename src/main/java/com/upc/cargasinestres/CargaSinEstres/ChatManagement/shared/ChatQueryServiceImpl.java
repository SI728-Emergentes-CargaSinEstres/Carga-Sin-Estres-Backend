package com.upc.cargasinestres.CargaSinEstres.ChatManagement.shared;

import com.upc.cargasinestres.CargaSinEstres.ChatManagement.model.entity.Chat;
import com.upc.cargasinestres.CargaSinEstres.ChatManagement.repository.IChatRepository;
import com.upc.cargasinestres.CargaSinEstres.ChatManagement.service.impl.ChatServiceImpl;
import org.springframework.stereotype.Service;

@Service
class ChatQueryServiceImpl implements ChatQueryService {

    private final IChatRepository chatRepository;
    private final ChatServiceImpl chatService;

    ChatQueryServiceImpl(IChatRepository chatRepository, ChatServiceImpl chatService) {
        this.chatRepository = chatRepository;
        this.chatService = chatService;
    }

    @Override
    public Chat createChat(Long reservationId) {
        return chatService.createChat(reservationId);
    }
}
