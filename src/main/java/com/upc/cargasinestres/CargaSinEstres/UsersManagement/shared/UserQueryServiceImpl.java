package com.upc.cargasinestres.CargaSinEstres.UsersManagement.shared;

import com.upc.cargasinestres.CargaSinEstres.UsersManagement.model.entity.Company;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.model.entity.Customer;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class UserQueryServiceImpl implements UserQueryService{

    private final ICustomerRepository customerRepository;
    private final ICompanyRepository companyRepository;


    public UserQueryServiceImpl(ICustomerRepository customerRepository, ICompanyRepository companyRepository) {
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Customer> existsByCustomerId(Long userId) {
        return customerRepository.findById(userId);
    }

    @Override
    public Optional<Company> existsByCompanyId(Long companyId) {
        return companyRepository.findById(companyId);
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }
}
