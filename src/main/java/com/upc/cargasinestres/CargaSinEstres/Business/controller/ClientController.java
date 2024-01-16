package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.request.ClientRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.response.ClientResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import com.upc.cargasinestres.CargaSinEstres.Business.service.IClientService;

import java.util.List;


/**
 * REST Client controller
 * @author Grupo1
 * @version 1.0
 */
@Tag(name="Client Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ClientController {

    private final IClientService clientService;

    /**
     * Class constructor
     * @param clientService The service for handling client operations.
     */
    public ClientController(IClientService clientService) {

        this.clientService = clientService;
    }

    /**
     * Retrieves a list of clients for login based on the provided email and password.
     *
     * @param email The email of the client.
     * @param password The password of the client.
     * @return A ResponseEntity containing the list of ClientResponseDto and HttpStatus.OK.
     */
    @Operation(summary = "Get clients for login")
    @GetMapping("/clients")
    public ResponseEntity<ClientResponseDto> getClientForLogin(@RequestParam(name="Email") String email, @RequestParam(name="Password")String password){
        var res = clientService.getClientForLogin(email, password);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Retrieves a client by their ID.
     *
     * @param id The ID of the client.
     * @return A ResponseEntity containing the ClientResponseDto and HttpStatus.OK.
     */
    @Operation(summary = "Get a client by Id")
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable Long id){
        var res = clientService.getClientById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Creates a new client based on the provided client data.
     *
     * @param clientRequestDto The data for creating the client.
     * @return A ResponseEntity containing the created ClientResponseDto and HttpStatus.CREATED.
     */
    @Operation(summary = "Create a Client")
    @PostMapping("/clients")
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody ClientRequestDto clientRequestDto) {
        var res = clientService.createClient(clientRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /**
     * Updates an existing client based on the provided client data.
     *
     * @param id The ID of the client to be updated.
     * @param clientRequestDto The updated data for the client.
     * @return A ResponseEntity containing the updated ClientResponseDto and HttpStatus.OK.
     */
    @Operation(summary = "Update a Client")
    @PutMapping("/clients/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable(name="id") Long id, @RequestBody ClientRequestDto clientRequestDto){
        var res = clientService.updateClient(id, clientRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}

