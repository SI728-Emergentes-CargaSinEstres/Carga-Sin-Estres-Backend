package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.request.ChatRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.response.ChatResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Chat controller for the management of chat related requests
 * Provides the methods to manage the chats in the database
 * @author Grupo1
 * @version 1.0
 */
@Tag(name="Chat Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class ChatController {

    private final IChatService chatService;

    public ChatController( IChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Creates a new chat for the bookingHistory it's related by Id to.
     * */
    @Operation(summary = "Create a Chat")
    @PostMapping("/bookingHistory/{id}/chat")
    public ResponseEntity<ChatResponseDto> createChat(@PathVariable(name="id") Long bookingId, @RequestParam(name = "userType") String userType,  @RequestBody ChatRequestDto chatRequestDto){
        var res = chatService.createChat(bookingId, userType, chatRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
