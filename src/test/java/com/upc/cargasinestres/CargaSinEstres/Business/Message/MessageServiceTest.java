package com.upc.cargasinestres.CargaSinEstres.Business.Message;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.request.MessageRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.response.MessageResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Chat;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Message;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IChatRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IMessageRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.Impl.MessageServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
    @Mock
    private IMessageRepository messageRepository;
    @Mock
    private IChatRepository chatRepository;
    @InjectMocks
    private MessageServiceImpl messageService;
    private MessageRequestDto messageRequestDto;
    private Message message;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        messageService = new MessageServiceImpl(messageRepository, chatRepository, new ModelMapper());
        messageRequestDto = new MessageRequestDto("La reserva será reprogramada para la fecha 02/05/24", "company");
        message = new Message();
    }

    @DisplayName("Test to create new message (Happy Path)")
    @Test
    void testCreateMessage_HappyPath() {
        // Arrange
        Chat chat = new Chat();
        chat.setReservationId(1L);
        when(chatRepository.findByReservationId(anyLong())).thenReturn(chat);
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        // Act
        MessageResponseDto result = messageService.createMessage(1L, messageRequestDto);

        // Assert
        assertNotNull(result);
    }

    @DisplayName("Test to create new message with reservation not found (Unhappy Path)")
    @Test
    void testCreateMessage_UnhappyPath_ReservationNotFound(){
        //Arrange
        when(chatRepository.findByReservationId(anyLong())).thenReturn(null);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            messageService.createMessage(1L, messageRequestDto);
        });
    }

    @DisplayName("Test to get messages by reservation id (Happy Path)")
    @Test
    void testGetMessagesByReservationId_HappyPath(){
        // Arrange
        Chat chat = new Chat();
        chat.setMessages(Collections.singletonList(new Message()));
        when(chatRepository.findByReservationId(anyLong())).thenReturn(chat);

        // Act
        List<MessageResponseDto> result = messageService.getMessagesByReservationId(1L);

        // Assert
        assertEquals(1, result.size());
    }

    @DisplayName("Test to get messages by reservation id with reservation not found (Unhappy Path)")
    @Test
    void testGetMessagesByReservationId_UnhappyPath_ReservationNotFound(){
        // Arrange
        when(chatRepository.findByReservationId(anyLong())).thenReturn(null);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            messageService.getMessagesByReservationId(1L);
        });
    }
}