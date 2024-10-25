package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Timeblock.TimeblockRequestDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.entity.Timeblock;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.repository.ITimeblockRepository;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.service.ITimeblockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
@Qualifier("TimeblockServiceImpl")
public class TimeblockServiceImpl implements ITimeblockService {

    private final ITimeblockRepository timeblockRepository;
    private final ModelMapper mapper;

    public TimeblockServiceImpl(ITimeblockRepository timeblockRepository, ModelMapper mapper) {
        this.timeblockRepository = timeblockRepository;
        this.mapper = mapper;
    }

    @Override
    public Timeblock createTimeblock(TimeblockRequestDto timeblock) {

        Timeblock newTimeblock = mapper.map(timeblock, Timeblock.class);
        newTimeblock.setStartTime(LocalTime.parse(timeblock.getStartTime()));
        newTimeblock.setEndTime(LocalTime.parse(timeblock.getEndTime()));

        return timeblockRepository.save(newTimeblock);
    }

    @Override
    public Timeblock getTimeblock(Long companyId) {
        return timeblockRepository.getByCompanyId(companyId);
    }

    @Override
    public Timeblock updateTimeblock(Long timeblockId, TimeblockRequestDto timeblock) {

        Optional<Timeblock> newTimeblock = Optional.of(timeblockRepository.getById(timeblockId));

        if (newTimeblock != null){
            newTimeblock.get().setStartTime(LocalTime.parse(timeblock.getStartTime()));
            newTimeblock.get().setEndTime(LocalTime.parse(timeblock.getEndTime()));
        }

        return timeblockRepository.save(newTimeblock.get());
    }
}
