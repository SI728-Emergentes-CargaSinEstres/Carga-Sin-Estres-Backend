package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.request.CompanyRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.response.CompanyResponseDto;

import java.util.List;

public interface ICompanyService {

    //get all companies
    public abstract List<CompanyResponseDto> getAllCompanies();

    //get company by id
    public abstract CompanyResponseDto getCompanyById(Long id);

    public abstract CompanyResponseDto getCompanyByName(String name);

    //create company
    public abstract CompanyResponseDto createCompany(CompanyRequestDto company);

    //update company
    public abstract CompanyResponseDto updateCompany(Long id, CompanyRequestDto companyRequestDto);

    //get companies for login
    public abstract CompanyResponseDto getCompanyForLogin(String email, String password);
}
