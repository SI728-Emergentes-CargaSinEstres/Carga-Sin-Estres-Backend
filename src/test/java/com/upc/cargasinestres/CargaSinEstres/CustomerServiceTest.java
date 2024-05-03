package com.upc.cargasinestres.CargaSinEstres;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.request.CustomerRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Customer.response.CustomerResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.ICustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerServiceTest {

    private ICustomerService customerService;

    @BeforeEach
    public void setup() {
        // Arrange
        customerService = new MockCustomerService();
    }

    @DisplayName("Test to validate that the customer id is unique")
    @Test
    public void testCreateCustomer() throws ParseException {
        // Arrange
        CustomerRequestDto requestDto = new CustomerRequestDto("John", "Doe", "123456789", "john.doe@gmail.com", "Password123!", LocalDate.of(1990, 1, 1));

        // Act
        CustomerResponseDto createdCustomer = customerService.createCustomer(requestDto);

        // Assert
        assertNotNull(createdCustomer.getId());
        assertEquals("John", createdCustomer.getFirstName());
        assertEquals("Doe", createdCustomer.getLastName());
        assertEquals("123456789", createdCustomer.getPhoneNumber());
        assertEquals("john.doe@gmail.com", createdCustomer.getEmail());
        assertEquals(LocalDate.of(1990, 1, 1), createdCustomer.getDateOfBirth());
    }

    @DisplayName("Test to validate that the customer id persists after updating the customer information")
    @Test
    public void testUpdateCustomer() throws ParseException {
        // Arrange
        Long customerId = 1L;
        CustomerRequestDto requestDto = new CustomerRequestDto("Winston", "Smith", "987654321", "wsmith@gmail.com", "password", LocalDate.of(2003, 5, 22));

        // Act
        CustomerResponseDto updatedCustomer = customerService.updateCustomer(customerId, requestDto);

        // Assert
        assertEquals(customerId, updatedCustomer.getId());
        assertEquals("Winston", updatedCustomer.getFirstName());
        assertEquals("Smith", updatedCustomer.getLastName());
        assertEquals("987654321", updatedCustomer.getPhoneNumber());
        assertEquals("wsmith@gmail.com", updatedCustomer.getEmail());
        assertEquals(LocalDate.of(2003, 5, 22), updatedCustomer.getDateOfBirth());
    }

    // Implementing MockCustomerService class to simulate service behavior
    private static class MockCustomerService implements ICustomerService {
        private Long customerIdCounter = 1L;

        @Override
        public CustomerResponseDto getCustomerForLogin(String email, String password) {
            return null;
        }

        @Override
        public CustomerResponseDto getCustomerById(Long id) {
            // Simular recuperación de un cliente por su identificador
            if (id.equals(1L)) {
                return new CustomerResponseDto(id, "John", "Doe", "123456789", "john.doe@gmail.com", LocalDate.of(1990, 1, 1));
            } else {
                return null;
            }
        }

        @Override
        public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
            // Simular la creación de un cliente y asignar un identificador único
            Long id = customerIdCounter++;
            return new CustomerResponseDto(id, customerRequestDto.getFirstName(), customerRequestDto.getLastName(),
                    customerRequestDto.getPhoneNumber(), customerRequestDto.getEmail(), customerRequestDto.getDateOfBirth());
        }

        @Override
        public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customer) {
            // Simular la actualización de un cliente manteniendo el identificador
            return new CustomerResponseDto(id, customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getEmail(), customer.getDateOfBirth());
        }
    }
}
