package com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.interfaces.service;

import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.dto.Customer.request.CustomerRequestDto;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.dto.Customer.response.CustomerResponseDto;

public interface ICustomerService {

    //GET
    public abstract CustomerResponseDto getCustomerForLogin(String email, String password);

    public abstract CustomerResponseDto getCustomerById(Long id);

    //CREATE
    public abstract CustomerResponseDto createCustomer(CustomerRequestDto client);

    //UPDATE
    public abstract CustomerResponseDto updateCustomer(Long id, CustomerRequestDto client);

}
