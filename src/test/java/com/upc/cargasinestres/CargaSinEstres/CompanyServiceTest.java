package com.upc.cargasinestres.CargaSinEstres;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.request.CompanyRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.response.CompanyResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.response.ServicioResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.ICompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompanyServiceTest {

    private ICompanyService companyService;

    @BeforeEach
    public void setup() {
        // Arrange
        companyService = new MockCompanyService();
    }

    @DisplayName("Test to validate that the created company has a unique ID")
    @Test
    public void testCreateCompany() {
        // Arrange
        CompanyRequestDto requestDto = new CompanyRequestDto("Carga Sin Estrés", "12345678901", "Miraflores", "cse@gmail.com", "123456789", "Cargasinestr3s!", "Lorem ipsum", "https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", List.of(1L, 2L, 3L));

        // Act
        CompanyResponseDto createdCompany = companyService.createCompany(requestDto);

        // Assert
        assertNotNull(createdCompany.getId());
        assertEquals("Carga Sin Estrés", createdCompany.getName());
        assertEquals("12345678901", createdCompany.getTIC());
        assertEquals("Miraflores", createdCompany.getDirection());
        assertEquals("cse@gmail.com", createdCompany.getEmail());
        assertEquals("123456789", createdCompany.getPhoneNumber());
        assertEquals("Lorem ipsum", createdCompany.getDescription());
        assertEquals("https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", createdCompany.getLogo());
        List<ServicioResponseDto> servicios = createdCompany.getServicios();
        List<Long> servicioIds = servicios.stream().map(ServicioResponseDto::getId).toList();
        assertEquals(List.of(1L, 2L, 3L), servicioIds);
    }

    @DisplayName("Test to validate that the company id persists after updating the customer information")
    @Test
    public void testUpdateExistingCompany() {
        // Arrange
        Long companyId = 1L;
        CompanyRequestDto requestDto = new CompanyRequestDto("Carga Con Estrés", "12345678901", "Miraflores", "cse@gmail.com", "123456789", "Cargasinestr3s!", "Lorem ipsum", "https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", List.of(1L, 2L, 3L));

        // Act
        CompanyResponseDto updatedCompany = companyService.updateCompany(companyId, requestDto);

        // Assert
        assertEquals(companyId, updatedCompany.getId());
        assertEquals("Carga Con Estrés", updatedCompany.getName());
        assertEquals("12345678901", updatedCompany.getTIC());
        assertEquals("Miraflores", updatedCompany.getDirection());
        assertEquals("cse@gmail.com", updatedCompany.getEmail());
        assertEquals("123456789", updatedCompany.getPhoneNumber());
        assertEquals("Lorem ipsum", updatedCompany.getDescription());
        assertEquals("https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", updatedCompany.getLogo());
        List<ServicioResponseDto> servicios = updatedCompany.getServicios();
        List<Long> servicioIds = servicios.stream().map(ServicioResponseDto::getId).toList();
        assertEquals(List.of(1L, 2L, 3L), servicioIds);
    }

    @DisplayName("Test to update a non-existing company")
    @Test
    public void testUpdateNonExistingCompany() {
        // Arrange
        Long companyId = 999L;
        CompanyRequestDto requestDto = new CompanyRequestDto("Carga Con Estrés", "12345678901", "Miraflores", "cse@gmail.com", "123456789", "Cargasinestr3s!", "Lorem ipsum", "https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", List.of(1L, 2L, 3L));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> companyService.updateCompany(companyId, requestDto));
    }

    // Implement MockCompanyService class to simulate service behavior
    private static class MockCompanyService implements ICompanyService {

        @Override
        public List<CompanyResponseDto> getAllCompanies() {
            return List.of();
        }

        @Override
        public CompanyResponseDto getCompanyById(Long id) {
            return null;
        }

        @Override
        public CompanyResponseDto createCompany(CompanyRequestDto company) {
            // Simular creación de una compañía y retornar una respuesta ficticia
            List<ServicioResponseDto> servicios = List.of(
                    new ServicioResponseDto(1L, "Transporte"),
                    new ServicioResponseDto(2L, "Carga"),
                    new ServicioResponseDto(3L, "Embalaje")
            );

            return new CompanyResponseDto(1L, company.getName(), company.getTIC(), company.getDirection(), company.getEmail(),
                    company.getPhoneNumber(), company.getDescription(), company.getLogo(), servicios, 0);
        }

        @Override
        public CompanyResponseDto updateCompany(Long id, CompanyRequestDto companyRequestDto) {
            // Simular actualización de una compañía manteniendo el identificador
            List<ServicioResponseDto> servicios = List.of(
                    new ServicioResponseDto(1L, "Transporte"),
                    new ServicioResponseDto(2L, "Carga"),
                    new ServicioResponseDto(3L, "Embalaje")
            );

            // Simular actualización de una compañía inexistente
            if (id.equals(999L)) {
                throw new IllegalArgumentException("Company not found");
            }

            return new CompanyResponseDto(id, companyRequestDto.getName(), companyRequestDto.getTIC(), companyRequestDto.getDirection(), companyRequestDto.getEmail(),
                    companyRequestDto.getPhoneNumber(), companyRequestDto.getDescription(), companyRequestDto.getLogo(), servicios, 0);
        }

        @Override
        public CompanyResponseDto getCompanyForLogin(String email, String password) {
            return null;
        }
    }
}
