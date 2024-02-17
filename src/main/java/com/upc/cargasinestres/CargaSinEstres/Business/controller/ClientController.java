package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.request.CustomerRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.response.CustomerResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import com.upc.cargasinestres.CargaSinEstres.Business.service.ICustomerService;


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

    private final ICustomerService clientService;

    /**
     * Class constructor
     * @param clientService The service for handling client operations.
     */
    public ClientController(ICustomerService clientService) {

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
    public ResponseEntity<CustomerResponseDto> getClientForLogin(@RequestParam(name="Email") String email, @RequestParam(name="Password")String password){
        var res = clientService.getCustomerForLogin(email, password);
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
    public ResponseEntity<CustomerResponseDto> getClientById(@PathVariable Long id){

        var res = clientService.getCustomerById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Creates a new client based on the provided client data.
     *
     * @param customerRequestDto The data for creating the client.
     * @return A ResponseEntity containing the created ClientResponseDto and HttpStatus.CREATED.
     */
    @Operation(summary = "Create a Client")
    @PostMapping("/clients")
    public ResponseEntity<CustomerResponseDto> createClient(@RequestBody CustomerRequestDto customerRequestDto) {
        var res = clientService.createCustomer(customerRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /**
     * Updates an existing client based on the provided client data.
     *
     * @param id The ID of the client to be updated.
     * @param customerRequestDto The updated data for the client.
     * @return A ResponseEntity containing the updated ClientResponseDto and HttpStatus.OK.
     */
    @Operation(summary = "Update a Client")
    @PutMapping("/clients/{id}")
    public ResponseEntity<CustomerResponseDto> updateClient(@PathVariable(name="id") Long id, @RequestBody CustomerRequestDto customerRequestDto){
        var res = clientService.updateCustomer(id, customerRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}

