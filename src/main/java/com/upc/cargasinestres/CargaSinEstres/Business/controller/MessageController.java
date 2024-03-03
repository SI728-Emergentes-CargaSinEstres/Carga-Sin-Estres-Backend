package com.upc.cargasinestres.CargaSinEstres.Business.controller;


import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.request.MessageRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Message.response.MessageResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IMessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Message Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class MessageController {

    private final IMessageService messageService;

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages/{idChat}")
    public ResponseEntity<MessageResponseDto> createMessage(@PathVariable Long idChat ,@RequestBody MessageRequestDto messageRequestDto){

        var res = messageService.createMessage(idChat, messageRequestDto);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
