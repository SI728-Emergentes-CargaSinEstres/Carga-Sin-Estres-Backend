package com.upc.cargasinestres.CargaSinEstres.UsersManagement.service;

import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.dto.Customer.request.CustomerRequestDto;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.dto.Customer.response.CustomerResponseDto;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.entity.Customer;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.interfaces.repository.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private CustomerResponseDto customerResponseDto;
    private CustomerRequestDto customerRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, modelMapper);
        customerRequestDto = new CustomerRequestDto();
        customer = new Customer();
        customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setEmail("lu@gmail.com");
    }

    @DisplayName("Test to get Customer for login (happy path)")
    @Test
    void testGetCustomerForLogin_HappyPath() {
        // Arrange
        String email = "lu@gmail.com";
        String password = "Password123";

        when(customerRepository.findByEmailAndPassword(email, password)).thenReturn(customer);
        when(modelMapper.map(customer, CustomerResponseDto.class)).thenReturn(customerResponseDto);

        // Act
        CustomerResponseDto result = customerService.getCustomerForLogin(email, password);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @DisplayName("Test to get Customer for login (unhappy path)")
    @Test
    void testGetCustomerForLogin_UnhappyPath() {

        // Arrange
        when(customerRepository.findByEmailAndPassword("nonexistent@example.com", "wrongpassword"))
                .thenReturn(null); // Explicitly set to return null for unhappy path

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () ->
                customerService.getCustomerForLogin("nonexistent@example.com", "wrongpassword"));
    }
}
