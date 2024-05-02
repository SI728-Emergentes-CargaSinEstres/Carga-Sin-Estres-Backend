package com.upc.cargasinestres.CargaSinEstres.Business.Customer;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.request.CustomerRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.response.CustomerResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Customer;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICustomerRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.Impl.CustomerServiceImpl;

import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private ICustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;
    private CustomerRequestDto customerRequestDto;
    private Customer customer;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, new ModelMapper());
        customerRequestDto = new CustomerRequestDto();
        customer = new Customer();
    }

    @DisplayName("Test to get Customer for login (happy path)")
    @Test
    void testGetCustomerForLogin_HappyPath() {
        // Arrange
        when(customerRepository.findByEmailAndPassword(any(String.class), any(String.class))).
                thenReturn(Optional.ofNullable(customer));

        // Act
        CustomerResponseDto result = customerService.getCustomerForLogin("lu@gmail.com", "Lucero123");

        // Assert
        assertNotNull(result);
    }

    @DisplayName("Test to get Customer for login (unhappy path)")
    @Test
    void testGetCustomerForLogin_UnhappyPath() {
        // Arrange
        when(customerRepository.findByEmailAndPassword(any(String.class), any(String.class))).thenReturn(null);

        // Act and assert
        assertThrows(ResourceNotFoundException.class, () ->
                customerService.getCustomerForLogin("romabe@gmail.com", "Romabe123456"));
    }

}
