package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.repository;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity.CompanyServiceViolation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ICompanyViolationServiceRepository extends JpaRepository<CompanyServiceViolation, Long> {
    int countByCompanyIdAndViolationDateBetween(Long companyId, LocalDate startDate, LocalDate endDate);
}
