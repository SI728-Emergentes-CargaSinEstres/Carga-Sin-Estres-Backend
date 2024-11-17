package com.upc.cargasinestres.CargaSinEstres.ContractManagement.application.controller;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.AdditionalWaitingTime.request.AdditionalWaitingTimeRequestDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.CompanyServiceViolation.request.CompanyServiceViolationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.service.IBusinessRulesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name="Business Rules Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class BusinessRulesController {
    private final IBusinessRulesService businessRulesService;

    public BusinessRulesController(IBusinessRulesService businessRulesService) {
        this.businessRulesService = businessRulesService;
    }

    @Operation(summary = "Get additional waiting time by customer id")
    @GetMapping("/additional-waiting-time/{customerId}")
    public ResponseEntity<?> getAdditionalWaitingTimeByCustomerId(@PathVariable Long customerId){
        var res = businessRulesService.getAdditionalWaitingTimeByCustomerId(customerId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create additional waiting time")
    @PostMapping("/additional-waiting-time")
    public ResponseEntity<?> createAdditionalWaitingTime(@RequestBody AdditionalWaitingTimeRequestDto additionalWaitingTimeRequestDto){
        var res = businessRulesService.createAdditionalWaitingTime(additionalWaitingTimeRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete additional waiting time")
    @DeleteMapping("/additional-waiting-time")
    public ResponseEntity<?> deleteAdditionalWaitingTime(){
        businessRulesService.deleteAdditionalWaitingTime();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get company service violation count by company id and year")
    @GetMapping("/company-service-violation/{companyId}/{year}")
    public ResponseEntity<?> getCompanyServiceViolationCountByCompanyIdAndYear(@PathVariable Long companyId, @PathVariable int year){
        var res = businessRulesService.getCompanyServiceViolationCountByCompanyIdAndYear(companyId, year);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Create company service violation")
    @PostMapping("/company-service-violation")
    public ResponseEntity<?> createCompanyServiceViolation(@RequestBody CompanyServiceViolationRequestDto companyServiceViolationRequestDto){
        var res = businessRulesService.createCompanyServiceViolation(companyServiceViolationRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
