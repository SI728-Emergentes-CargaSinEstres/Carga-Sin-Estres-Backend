package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Timeblock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITimeblockRepository  extends JpaRepository<Timeblock, Long> {

    Timeblock getByCompanyId(Long companyId);


}
