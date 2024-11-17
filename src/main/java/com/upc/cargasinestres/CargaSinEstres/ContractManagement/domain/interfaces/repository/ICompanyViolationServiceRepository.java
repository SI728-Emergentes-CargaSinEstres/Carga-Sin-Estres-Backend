package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.repository;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity.CompanyServiceViolation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICompanyViolationServiceRepository extends JpaRepository<CompanyServiceViolation, Long> {
    List<CompanyServiceViolation> findByCompanyIdAndViolationDateBetween(Long companyId, LocalDate startDate, LocalDate endDate);
    List<CompanyServiceViolation> findByCompanyId(Long companyId);
    List<CompanyServiceViolation> findByViolationDateBetween(LocalDate startDate, LocalDate endDate);
    Optional<CompanyServiceViolation> findTopByOrderByIdDesc();

}
