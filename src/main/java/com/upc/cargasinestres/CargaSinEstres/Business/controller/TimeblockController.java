package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Timeblock.TimeblockRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Timeblock;
import com.upc.cargasinestres.CargaSinEstres.Business.service.ITimeblockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;

@Tag(name = "Timeblock Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/timeblock/")
public class TimeblockController {

    private final ITimeblockService timeblockService;

    public TimeblockController(ITimeblockService timeblockService) {
        this.timeblockService = timeblockService;
    }

    @GetMapping("{companyId}")
    public Timeblock getTimeblockByCompanyId(@PathVariable Long companyId){
        return timeblockService.getTimeblock(companyId);
    }

    @PostMapping("")
    public Timeblock createTimeblock( @RequestBody TimeblockRequestDto timeblock){
        return timeblockService.createTimeblock(timeblock);
    }

    @PutMapping("{timeblockId}")
    public Timeblock updateTimeblock(@PathVariable Long timeblockId, @RequestBody TimeblockRequestDto timeblock){
        return timeblockService.updateTimeblock(timeblockId, timeblock);
    }
}
