package com.upc.cargasinestres.CargaSinEstres;

import com.upc.cargasinestres.CargaSinEstres.Business.controller.CompanyController;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.request.CompanyRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.response.CompanyResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.response.ServicioResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.ICompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyControllerTest {

    private CompanyController companyController;
    private ICompanyService companyService;

    @BeforeEach
    public void setup() {
        // Arrange
        companyService = new MockCompanyService();
        companyController = new CompanyController(companyService);
    }

    @Test
    public void testCreateCompany() {
        // Arrange
        CompanyRequestDto requestDto = new CompanyRequestDto("Carga Sin Estrés", "12345678901", "Miraflores", "cse@gmail.com", "123456789", "Cargasinestr3s!", "Lorem ipsum", "https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", List.of(1L, 2L, 3L));

        // Act
        ResponseEntity<CompanyResponseDto> response = companyController.createCompany(requestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        CompanyResponseDto createdCompany = response.getBody();
        assertEquals("Carga Sin Estrés", createdCompany.getName());
        assertEquals("12345678901", createdCompany.getTIC());
        assertEquals("Miraflores", createdCompany.getDirection());
        assertEquals("cse@gmail.com", createdCompany.getEmail());
        assertEquals("123456789", createdCompany.getPhoneNumber());
        assertEquals("Lorem ipsum", createdCompany.getDescription());
        assertEquals("https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", createdCompany.getLogo());
        List<ServicioResponseDto> servicios = createdCompany.getServicios();
        List<Long> servicioIds = servicios.stream().map(ServicioResponseDto::getId).collect(Collectors.toList());
        assertEquals(List.of(1L, 2L, 3L), servicioIds);
    }

    @Test
    public void testUpdateCompany() {
        // Arrange
        Long companyId = 1L; // ID de la compañía a actualizar
        CompanyRequestDto requestDto = new CompanyRequestDto("Carga Con Estrés", "12345678901", "Miraflores", "cse@gmail.com", "123456789", "Cargasinestr3s!", "Lorem ipsum", "https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", List.of(1L, 2L, 3L));

        // Act
        ResponseEntity<CompanyResponseDto> response = companyController.updateCompany(companyId, requestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CompanyResponseDto updatedCompany = response.getBody();
        assertEquals("Carga Con Estrés", updatedCompany.getName());
        assertEquals("12345678901", updatedCompany.getTIC());
        assertEquals("Miraflores", updatedCompany.getDirection());
        assertEquals("cse@gmail.com", updatedCompany.getEmail());
        assertEquals("123456789", updatedCompany.getPhoneNumber());
        assertEquals("Lorem ipsum", updatedCompany.getDescription());
        assertEquals("https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", updatedCompany.getLogo());
        List<ServicioResponseDto> servicios = updatedCompany.getServicios();
        List<Long> servicioIds = servicios.stream().map(ServicioResponseDto::getId).collect(Collectors.toList());
        assertEquals(List.of(1L, 2L, 3L), servicioIds);
    }

    @Test
    public void TestGetCompanyForLogin(){
        // Arrange
        String email = "cse@gmail.com";
        String password = "CSE123";

        // Act
        ResponseEntity<CompanyResponseDto> response = companyController.getCompanyForLogin(email, password);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CompanyResponseDto expectedResponse = response.getBody();
        assertEquals("Carga Con Estrés", expectedResponse.getName());
        assertEquals("12345678901", expectedResponse.getTIC());
        assertEquals("Miraflores", expectedResponse.getDirection());
        assertEquals("cse@gmail.com", expectedResponse.getEmail());
        assertEquals("123456789", expectedResponse.getPhoneNumber());
        assertEquals("Lorem ipsum", expectedResponse.getDescription());
        assertEquals("https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", expectedResponse.getLogo());
        List<ServicioResponseDto> servicios = expectedResponse.getServicios();
        List<Long> servicioIds = servicios.stream().map(ServicioResponseDto::getId).collect(Collectors.toList());
        assertEquals(List.of(1L, 2L, 3L), servicioIds);
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
            // Simulate creation of company and return a dummy response
            List<ServicioResponseDto> servicios = new ArrayList<>();
            servicios.add(new ServicioResponseDto(1L, "Transporte"));
            servicios.add(new ServicioResponseDto(2L, "Carga"));
            servicios.add(new ServicioResponseDto(3L, "Embalaje"));

            return new CompanyResponseDto(1L, company.getName(), company.getTIC(), company.getDirection(), company.getEmail(),
                    company.getPhoneNumber(), company.getDescription(), company.getLogo(), servicios, 0);

        }

        @Override
        public CompanyResponseDto updateCompany(Long id, CompanyRequestDto companyRequestDto) {
            List<ServicioResponseDto> servicios = new ArrayList<>();
            servicios.add(new ServicioResponseDto(1L, "Transporte"));
            servicios.add(new ServicioResponseDto(2L, "Carga"));
            servicios.add(new ServicioResponseDto(3L, "Embalaje"));

            return new CompanyResponseDto(id, companyRequestDto.getName(), companyRequestDto.getTIC(), companyRequestDto.getDirection(), companyRequestDto.getEmail(),
                    companyRequestDto.getPhoneNumber(), companyRequestDto.getDescription(), companyRequestDto.getLogo(), servicios, 0);
        }

        @Override
        public CompanyResponseDto getCompanyForLogin(String email, String password) {
            return new CompanyResponseDto(1L, "Carga Con Estrés", "12345678901", "Miraflores", email, "123456789", "Lorem ipsum", "https://transporteromabe.com/wp-content/uploads/2020/02/para-todo.jpg", List.of(new ServicioResponseDto(1L, "Transporte"), new ServicioResponseDto(2L, "Carga"), new ServicioResponseDto(3L, "Embalaje")), 0);
        }
    }
}