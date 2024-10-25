package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.repository;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.entity.Timeblock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITimeblockRepository  extends JpaRepository<Timeblock, Long> {

    Timeblock getByCompanyId(Long companyId);


}