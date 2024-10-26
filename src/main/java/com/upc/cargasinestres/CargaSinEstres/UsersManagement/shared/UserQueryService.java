package com.upc.cargasinestres.CargaSinEstres.UsersManagement.shared;

import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.entity.Company;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.entity.Customer;

import java.util.Optional;

public interface UserQueryService {

   Optional<Customer> existsByCustomerId(Long userId);

   Optional<Company> existsByCompanyId(Long companyId);

   void saveCompany(Company company);
}

