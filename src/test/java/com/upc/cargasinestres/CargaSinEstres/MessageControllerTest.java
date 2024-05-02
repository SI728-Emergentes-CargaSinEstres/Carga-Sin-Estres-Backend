package com.upc.cargasinestres.CargaSinEstres;

import com.upc.cargasinestres.CargaSinEstres.Business.controller.CompanyController;
import com.upc.cargasinestres.CargaSinEstres.Business.controller.MessageController;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.request.MessageRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.response.MessageResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageControllerTest {

    private MessageController messageController;
    private IMessageService messageService;

    @BeforeEach
    public void setup() {
        // Arrange
        messageService = new MockMessageService();
        messageController = new MessageController(messageService);
    }

    @Test
    public void testCreateMessage() {
        // Arrange
        MessageRequestDto requestDto = new MessageRequestDto("Buenos días, ha surgido un incoveniente con el transporte, podría ser reprogramada la reserva?", "company");

        // Act
        ResponseEntity<MessageResponseDto> response = messageController.createMessage(1L, requestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        MessageResponseDto createdMessage = response.getBody();
        assertEquals("Buenos días, ha surgido un incoveniente con el transporte, podría ser reprogramada la reserva?", createdMessage.getContent());
        assertEquals("company", createdMessage.getUserType());
    }

    @Test
    public void testGetMessagesByReservationId() {
        // Arrange
        Long reservationId = 1L;

        // Act
        ResponseEntity<List<MessageResponseDto>> response = messageController.getMessagesByReservationId(reservationId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<MessageResponseDto> messages = response.getBody();
        assertEquals(2, messages.size());
        List<String> contents = messages.stream().map(MessageResponseDto::getContent).collect(Collectors.toList());
        assertEquals(List.of("Buenos días, ha surgido un incoveniente con el transporte, podría ser reprogramada la reserva?",
                "La reserva será reprogramada para la fecha 01/05/24"), contents);
    }


    // Implement MockCompanyService class to simulate service behavior
    private static class MockMessageService implements IMessageService {

        @Override
        public MessageResponseDto createMessage(Long reservationId, MessageRequestDto messageRequestDto) {
            return new MessageResponseDto(1L, messageRequestDto.getContent(), LocalDateTime.now(),messageRequestDto.getUserType());
        }

        @Override
        public List<MessageResponseDto> getMessagesByReservationId(Long reservationId) {
            return List.of(
                    new MessageResponseDto(reservationId, "Buenos días, ha surgido un incoveniente con el transporte, podría ser reprogramada la reserva?", LocalDateTime.now(), "company"),
                    new MessageResponseDto(reservationId, "La reserva será reprogramada para el 01/05/24", LocalDateTime.now(), "company")
            );
        }
    }
}