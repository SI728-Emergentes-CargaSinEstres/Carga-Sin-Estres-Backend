package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Timeblock.TimeblockRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Timeblock;

public interface ITimeblockService {

    public abstract Timeblock createTimeblock(TimeblockRequestDto timeblock);

    public abstract Timeblock getTimeblock(Long companyId);

    public abstract Timeblock updateTimeblock(Long timeblockId, TimeblockRequestDto timeblock);
}
