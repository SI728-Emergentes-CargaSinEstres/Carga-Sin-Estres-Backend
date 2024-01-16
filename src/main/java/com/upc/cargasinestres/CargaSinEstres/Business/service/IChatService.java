package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.request.ChatRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.response.ChatResponseDto;

import java.util.List;

/**
 * Service for Chat entity
 * This interface provides the methods for CRUD operations using the Chat entity
 * The methods are implemented by Spring Data JPA The methods are:
 * <ul>
 *     <li> createChat - Create a new Chat</li>
 * </ul>
 * @author Grupo1
 * @version 1.0
 */
public interface IChatService {

    /**
     * Create a new Chat with the information provided as a parameter
     * @param bookingId The booking id
     * @param chatRequestDto The chat information
     * @return The created chat information
     */
    public abstract ChatResponseDto createChat(Long bookingId, String userType, ChatRequestDto chatRequestDto);

    /**
     * Get all the chats by booking history id
     * @param bookingHistoryId The booking history id
     * @return The list of chats
     */
    public abstract List<ChatResponseDto> getChatsByBookingHistoryId(Long bookingHistoryId);
}
