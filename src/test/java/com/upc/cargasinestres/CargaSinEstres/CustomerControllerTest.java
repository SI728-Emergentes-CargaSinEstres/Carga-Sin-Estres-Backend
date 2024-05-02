package com.upc.cargasinestres.CargaSinEstres;

import com.upc.cargasinestres.CargaSinEstres.Business.controller.CustomerController;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.request.CustomerRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.response.CustomerResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.ICustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest {

    private CustomerController customerController;
    private ICustomerService customerService;

    @BeforeEach
    public void setup() {
        // Arrange
        customerService = new MockCustomerService();
        customerController = new CustomerController(customerService);
    }

    @Test
    public void testCreateCustomer() throws ParseException {
        // Arrange
        CustomerRequestDto requestDto = new CustomerRequestDto("John", "Doe", "123456789", "john.doe@gmail.com", "Password123!", LocalDate.of(1990, 1, 1));

        // Act
        ResponseEntity<CustomerResponseDto> response = customerController.createCustomer(requestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        CustomerResponseDto createdCustomer = response.getBody();
        assertEquals("John", createdCustomer.getFirstName());
        assertEquals("Doe", createdCustomer.getLastName());
        assertEquals("Password123!", createdCustomer.getPhoneNumber());
        assertEquals("john.doe@gmail.com", createdCustomer.getEmail());
        assertEquals(LocalDate.of(1990, 1, 1), createdCustomer.getDateOfBirth());
    }

    @Test
    public void testUpdateCustomer() throws ParseException {
        // Arrange
        Long customerId = 1L;
        CustomerRequestDto requestDto = new CustomerRequestDto("Winston", "Smith", "987654321", "wsmith@gmail.com", "password", LocalDate.of(2003, 5, 22));

        //Act
        ResponseEntity<CustomerResponseDto> response = customerController.updateCustomer(customerId, requestDto);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CustomerResponseDto updatedCustomer = response.getBody();
        assertEquals("Winston", updatedCustomer.getFirstName());
        assertEquals("Smith", updatedCustomer.getLastName());
        assertEquals("987654321", updatedCustomer.getPhoneNumber());
        assertEquals("wsmith@gmail.com", updatedCustomer.getEmail());
        assertEquals(LocalDate.of(2003, 5, 22), updatedCustomer.getDateOfBirth());
    }

    // Implementing MockCustomerService class to simulate service behavior
    private static class MockCustomerService implements ICustomerService {
        @Override
        public CustomerResponseDto getCustomerForLogin(String email, String password) {
            return null;
        }

        @Override
        public CustomerResponseDto getCustomerById(Long id) {
            return null;
        }

        @Override
        public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
            return new CustomerResponseDto(1L, customerRequestDto.getFirstName(), customerRequestDto.getLastName(),
                    customerRequestDto.getPhoneNumber(), customerRequestDto.getEmail(), customerRequestDto.getDateOfBirth());
        }

        @Override
        public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customer) {
            return new CustomerResponseDto(id, customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getEmail(), customer.getDateOfBirth());
        }
    }
}
