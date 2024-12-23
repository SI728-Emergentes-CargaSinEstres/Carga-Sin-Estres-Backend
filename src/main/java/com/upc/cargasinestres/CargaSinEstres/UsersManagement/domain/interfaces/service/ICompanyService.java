package com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.interfaces.service;

import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.dto.Company.request.CompanyRequestDto;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.dto.Company.response.CompanyResponseDto;

import java.util.List;

public interface ICompanyService {

    //get all companies
    public abstract List<CompanyResponseDto> getAllCompanies();

    //get company by id
    public abstract CompanyResponseDto getCompanyById(Long id);

    //create company
    public abstract CompanyResponseDto createCompany(CompanyRequestDto company);

    //update company
    public abstract CompanyResponseDto updateCompany(Long id, CompanyRequestDto companyRequestDto);

    //get companies for login
    public abstract CompanyResponseDto getCompanyForLogin(String email, String password);
    public abstract CompanyResponseDto getCompanyByName(String name);
}
