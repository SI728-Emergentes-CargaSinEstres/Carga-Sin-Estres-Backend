package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.ClientValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.response.CompanyResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.request.ClientRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.response.ClientResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Client;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IClientRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation of the IClientService interface.
 * Handles the business logic for client operations.
 * @author Grupo1
 * @version 1.0
 * */
@Service
public class ClientServiceImpl implements IClientService{


    private final IClientRepository clientRepository;
    private final ModelMapper modelMapper;

    //inyeccion de dependencias
    public ClientServiceImpl(IClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new client.
     *
     * @param clientRequestDto The data for creating the client.
     * @return The created client response.
     * @throws RuntimeException if a client with the same email and password already exists.
     */
    @Override
    public ClientResponseDto createClient(ClientRequestDto clientRequestDto) {

        if(clientRepository.existsClient(clientRequestDto.getEmail(), clientRequestDto.getPassword()).isPresent())
            throw new RuntimeException("Ya existe un cliente con ese email y password");

        ClientValidation.ValidateClient(clientRequestDto);

        var newClient = modelMapper.map(clientRequestDto, Client.class);
        newClient.setUserType("client");
        var createdClient = clientRepository.save(newClient);
        return modelMapper.map(createdClient, ClientResponseDto.class);
    }

    /**
     * Retrieves a list of clients for login based on email and password.
     *
     * @param email    The client's email.
     * @param password The client's password.
     * @return A list of client response DTOs.
     */
    @Override
    public ClientResponseDto getClientForLogin(String email, String password) {
        var client = clientRepository.findByEmailAndPassword(email, password);
        if (client == null)
            throw new ResourceNotFoundException("No existe una empresa con ese email y password"); // se valida

        return modelMapper.map(client, ClientResponseDto.class);
    }


    @Override
    public ClientResponseDto getClientById(Long id) {
        var client = clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro el cliente con id: "+id));
        return modelMapper.map(client, ClientResponseDto.class);
    }

    @Override
    public ClientResponseDto updateClient(Long id, ClientRequestDto clientRequestDto){
        var client = clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro el cliente con id: "+id));

        modelMapper.map(clientRequestDto, client);

        Client updateClient = clientRepository.save(client);

        return modelMapper.map(updateClient, ClientResponseDto.class);

    }


}
