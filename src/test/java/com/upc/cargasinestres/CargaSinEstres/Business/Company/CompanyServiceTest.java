package com.upc.cargasinestres.CargaSinEstres.Business.Company;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.request.CompanyRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.response.CompanyResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IServicioRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.Impl.CompanyServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @Mock
    private ICompanyRepository companyRepository;
    @Mock
    private IServicioRepository servicioRepository;
    @InjectMocks
    private CompanyServiceImpl companyService;
    private CompanyRequestDto companyRequestDto;
    private Company company;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        companyService = new CompanyServiceImpl(companyRepository, servicioRepository, new ModelMapper());
        companyRequestDto = new CompanyRequestDto();
        company = new Company();
    }

    @DisplayName("Test to get company for login (happy path)")
    @Test
    void testGetCompanyForLogin_HappyPath() {
        //Arrange
        when(companyRepository.findByEmailAndPassword(any(String.class), any(String.class)))
                .thenReturn(Optional.ofNullable(company));

        //Act
        CompanyResponseDto result = companyService.getCompanyForLogin("romabe@gmail.com", "Romabe123");

        //Assert
        assertNotNull(result);
    }

    @DisplayName("Test to get company for login (unhappy path)")
    @Test
    void testGetCompanyForLogin_UnhappyPath() {
        //Arrange
        when(companyRepository.findByEmailAndPassword(any(String.class), any(String.class)))
                .thenReturn(Optional.empty());

        //Act and Assert
        assertThrows(Exception.class, () ->
                companyService.getCompanyForLogin("romabe@gmail.com", "Romabe123456"));
    }
}
