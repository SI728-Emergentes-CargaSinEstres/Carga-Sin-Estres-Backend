package com.upc.cargasinestres.CargaSinEstres.ChatManagement.service;

import com.upc.cargasinestres.CargaSinEstres.ChatManagement.domain.entity.Chat;
import com.upc.cargasinestres.CargaSinEstres.ChatManagement.domain.interfaces.repository.IChatRepository;
import com.upc.cargasinestres.CargaSinEstres.ChatManagement.domain.interfaces.service.IChatService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements IChatService {

    private final IChatRepository chatRepository;

    public ChatServiceImpl(IChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Long getChatByReservationId(Long reservationId) {
        var chat = chatRepository.findByReservationId(reservationId);
        return chat.getId();
    }

    @Override
    public Chat createChat(Long reservationId) {
        var chat = chatRepository.findByReservationId(reservationId);
        if (chat != null) {
            throw new RuntimeException("Ya existe un chat para esta empresa");
        }
        chat = new Chat();

        chat.setReservationId(reservationId);

        chatRepository.save(chat);

        return chat; // se envia un objeto chat con el companyId ya seteado
    }


}
