package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.request.MessageRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.response.MessageResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Message;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IChatRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IMessageRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IMessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("messageServiceImpl")
public class MessageServiceImpl implements IMessageService {

    private final IMessageRepository messageRepository;
    private final IChatRepository chatRepository;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(IMessageRepository messageRepository, IChatRepository chatRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MessageResponseDto createMessage(Long chatId, MessageRequestDto messageRequestDto) {

        if (chatRepository.findById(chatId).isEmpty())
            throw new RuntimeException("No existe el chat con id " + chatId);

        var newMessage = modelMapper.map(messageRequestDto, Message.class);

        newMessage.setChatId(chatId);

        var createdMessage = messageRepository.save(newMessage);

        return modelMapper.map(createdMessage, MessageResponseDto.class);
    }

}
