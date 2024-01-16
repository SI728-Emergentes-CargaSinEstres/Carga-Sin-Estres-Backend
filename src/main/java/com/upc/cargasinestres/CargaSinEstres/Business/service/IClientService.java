package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.request.ClientRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.response.ClientResponseDto;

import java.util.List;

public interface IClientService {

    //GET
    public abstract ClientResponseDto getClientForLogin(String email, String password);

    public abstract ClientResponseDto getClientById(Long id);

    //CREATE
    public abstract ClientResponseDto createClient(ClientRequestDto client);

    //UPDATE
    public abstract ClientResponseDto updateClient(Long id, ClientRequestDto client);

}
