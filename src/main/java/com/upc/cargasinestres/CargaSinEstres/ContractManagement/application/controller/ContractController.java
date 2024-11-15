package com.upc.cargasinestres.CargaSinEstres.ContractManagement.application.controller;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.Contract.request.ContractRequestDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.dto.Contract.response.ContractResponseDto;
import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.service.IContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Contract Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ContractController {

    private final IContractService contractService;

    public ContractController(IContractService contractService) {
        this.contractService = contractService;
    }

    @Operation(summary = "Create a contract")
    @PostMapping("/contracts")
    public ResponseEntity<ContractResponseDto> createContract(@RequestBody ContractRequestDto contractRequestDto) {
        var res = contractService.createContract(contractRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Get contract by reservation id")
    @GetMapping("/contracts/{reservationId}")
    public ResponseEntity<ContractResponseDto> getContractByReservationID(@PathVariable Long reservationId) {
        var res = contractService.getContractByReservationId(reservationId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
