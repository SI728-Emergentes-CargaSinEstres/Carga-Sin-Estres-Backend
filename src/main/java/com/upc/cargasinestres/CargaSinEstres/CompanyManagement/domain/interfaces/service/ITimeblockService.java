package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.interfaces.service;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.dto.Timeblock.TimeblockRequestDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.entity.Timeblock;

public interface ITimeblockService {

    public abstract Timeblock createTimeblock(TimeblockRequestDto timeblock);

    public abstract Timeblock getTimeblock(Long companyId);

    public abstract Timeblock updateTimeblock(Long timeblockId, TimeblockRequestDto timeblock);
}
