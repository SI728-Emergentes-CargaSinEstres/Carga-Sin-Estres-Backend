package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.interfaces.repository;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.entity.Timeblock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITimeblockRepository  extends JpaRepository<Timeblock, Long> {

    Timeblock getByCompanyId(Long companyId);


}
