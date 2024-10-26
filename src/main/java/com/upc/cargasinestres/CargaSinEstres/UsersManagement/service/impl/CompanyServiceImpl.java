package com.upc.cargasinestres.CargaSinEstres.UsersManagement.service.impl;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.shared.CompanyQueryService;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.validations.CompanyValidation;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.model.dto.Company.request.CompanyRequestDto;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.model.dto.Company.response.CompanyResponseDto;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.model.entity.Company;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.service.ICompanyService;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the ICompanyService interface.
 * Handles the business logic for company operations.
 *
 * @author Grupo1
 * @version 1.0
 */
@Service
class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository companyRepository;
    private final CompanyQueryService companyQueryService;
    private final ModelMapper modelMapper;

    //inyeccion de dependencias
    public CompanyServiceImpl(ICompanyRepository companyRepository, CompanyQueryService companyQueryService, ModelMapper modelMapper) {

        this.companyRepository = companyRepository;
        this.companyQueryService = companyQueryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        var companies = companyRepository.findAll();

        return companies.stream()
                .map(company -> {
                    int averageRating = companyQueryService.calculateAverageRating(company.getRatings());
                    boolean hasMembership = company.getMembership() != null;
                    CompanyResponseDto companyResponseDto = modelMapper.map(company, CompanyResponseDto.class);
                    companyResponseDto.setAverageRating(averageRating);
                    companyResponseDto.setHasMembership(hasMembership);
                    return companyResponseDto;
                })
                .toList();
    }



    @Override
    public CompanyResponseDto getCompanyById(Long id) {
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la empresa con id: " + id));

        int averageRating = companyQueryService.calculateAverageRating(company.getRatings());

        CompanyResponseDto companyResponseDto = modelMapper.map(company, CompanyResponseDto.class);
        companyResponseDto.setAverageRating(averageRating);

        return companyResponseDto;
    }

    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto) {
        if (companyRepository.findByName(companyRequestDto.getName()).isPresent())
            throw new RuntimeException("Ya existe una empresa con ese nombre");

        if (companyRepository.findByTIC(companyRequestDto.getTIC()).isPresent())
            throw new RuntimeException("Ya existe una empresa con ese RUC");

        if (companyRepository.findByEmail(companyRequestDto.getEmail()).isPresent())
            throw new RuntimeException("Ya existe una empresa con ese email");

        if (companyRepository.findByPhoneNumber(companyRequestDto.getPhoneNumber()).isPresent())
            throw new RuntimeException("Ya existe una empresa con ese número de teléfono");

        if (companyRepository.findByLogo(companyRequestDto.getLogo()).isPresent())
            throw new RuntimeException("Ya existe una empresa con ese logo");

        //obtener todos los id de servicios que existen
        List<Long> idsServiciosExistentes= companyQueryService.findAllIdsServices();

        CompanyValidation.ValidateCompany(companyRequestDto, idsServiciosExistentes);

        //List<Servicio> servicios = servicioRepository.findAllById(companyRequestDto.getServicioIds());
        var newCompany = modelMapper.map(companyRequestDto, Company.class);

        newCompany.setServicios(companyQueryService.findAllServicerById(companyRequestDto.getServicioIds()));

        var createdCompany = companyRepository.save(newCompany);
        return modelMapper.map(createdCompany, CompanyResponseDto.class);
    }

    @Override
    public CompanyResponseDto updateCompany(Long id, CompanyRequestDto companyRequestDto) {
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la empresa con id: " + id));

        //Validar que campos setear
        if (companyRequestDto.getName() != null) {
            CompanyValidation.validateCompanyName(companyRequestDto.getName());
            company.setName(companyRequestDto.getName());
        }
        if (companyRequestDto.getLogo() != null) {
            CompanyValidation.validateCompanyLogo(companyRequestDto.getLogo());
            company.setLogo(companyRequestDto.getLogo());
        }
        if (companyRequestDto.getDescription() != null) {
            CompanyValidation.validateCompanyDescription(companyRequestDto.getDescription());
            company.setDescription(companyRequestDto.getDescription());
        }
        if (companyRequestDto.getTIC() != null) {
            CompanyValidation.validateCompanyTIC(companyRequestDto.getTIC());
            company.setTIC(companyRequestDto.getTIC());
        }
        if (companyRequestDto.getPhoneNumber() != null) {
            CompanyValidation.validateCompanyPhoneNumber(companyRequestDto.getPhoneNumber());
            company.setPhoneNumber(companyRequestDto.getPhoneNumber());
        }
        if (companyRequestDto.getEmail() != null) {
            CompanyValidation.validateCompanyEmail(companyRequestDto.getEmail());
            company.setEmail(companyRequestDto.getEmail());
        }
        if (companyRequestDto.getDirection() != null) {
            CompanyValidation.validateCompanyDirection(companyRequestDto.getDirection());
            company.setDirection(companyRequestDto.getDirection());
        }
        if (companyRequestDto.getPassword() != null) {
            CompanyValidation.validateCompanyPassword(companyRequestDto.getPassword());
            company.setPassword(companyRequestDto.getPassword());
        }

        //obtener todos los id de servicios que existen
        List<Long> idsServiciosExistentes= companyQueryService.findAllIdsServices();

        if (companyRequestDto.getServicioIds() != null) {
            CompanyValidation.validateCompanyServices(companyRequestDto.getServicioIds(), idsServiciosExistentes);
            //List<Servicio> servicios = servicioRepository.findAllById(companyRequestDto.getServicioIds());
            company.setServicios(companyQueryService.findAllServicerById(companyRequestDto.getServicioIds()));
        }

        Company updatedCompany = companyRepository.save(company); // se guardan los cambios en la base de datos
        return modelMapper.map(updatedCompany, CompanyResponseDto.class); // se retorna un responseDTO con los datos del company actualizado
    }


    @Override
    public CompanyResponseDto getCompanyForLogin(String email, String password) {

        var company = companyRepository.findByEmailAndPassword(email, password); //se obtiene

        if (company.isEmpty())
            throw new ResourceNotFoundException("No existe una empresa con ese email y password"); // se valida

        return modelMapper.map(company, CompanyResponseDto.class); // se retorna un responseDTO con los datos del company
    }


    @Override
    public CompanyResponseDto getCompanyByName(String name) {
        var company = companyRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la empresa con nombre: " + name));
        int averageRating = companyQueryService.calculateAverageRating(company.getRatings());
        CompanyResponseDto companyResponseDto = modelMapper.map(company, CompanyResponseDto.class);
        companyResponseDto.setAverageRating(averageRating);
        return companyResponseDto;
    }

}
