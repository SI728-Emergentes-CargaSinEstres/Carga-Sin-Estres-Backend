package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.response.ClientResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.ICompanyService;
import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.CompanyValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Client;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.request.CompanyRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.response.CompanyResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

/**
 * Implementation of the ICompanyService interface.
 * Handles the business logic for company operations.
 * @author Grupo1
 * @version 1.0*/
@Service
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    //inyeccion de dependencias
    public CompanyServiceImpl(ICompanyRepository companyRepository, ModelMapper modelMapper) {

        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        var companies = companyRepository.findAll();

        return companies.stream().map(
                Company->modelMapper.map(Company, CompanyResponseDto.class)
        ).toList();
    }

    @Override
    public CompanyResponseDto getCompanyById(Long id) {
        var company = companyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro la empresa con id: "+id));
        return modelMapper.map(company, CompanyResponseDto.class);
    }

    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto) {
        if (companyRepository.findByNameAndNumeroContacto(companyRequestDto.getName(), companyRequestDto.getNumeroContacto()).isPresent())
            throw new RuntimeException("Ya existe una empresa con ese nombre y numero de contacto");

        CompanyValidation.ValidateCompany(companyRequestDto);

        var newCompany = modelMapper.map(companyRequestDto, Company.class);
        newCompany.setUserType("company");
        var createdCompany = companyRepository.save(newCompany);
        return modelMapper.map(createdCompany, CompanyResponseDto.class);
    }

    @Override
    public CompanyResponseDto updateCompany(Long id, CompanyRequestDto companyRequestDto){
        var company = companyRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro el company con id: "+id)); // se obtiene el company de la base de datos

        modelMapper.map(companyRequestDto, company); // se mapea el companyRequestDto a company y se actualiza el company

        Company updateCompany = companyRepository.save(company); // se guardan los cambios en la base de datos

        return modelMapper.map(updateCompany, CompanyResponseDto.class); // se retorna un responseDTO con los datos del company actualizado
    }

    @Override
    public CompanyResponseDto getCompanyForLogin(String email, String password) {

        var company = companyRepository.findCompanyByEmailAndPassword(email, password); //se obtiene

        if (company == null)
            throw new ResourceNotFoundException("No existe una empresa con ese email y password"); // se valida

        return modelMapper.map(company, CompanyResponseDto.class); // se retorna un responseDTO con los datos del company
    }
}
