package com.upc.cargasinestres.CargaSinEstres.Contract.controller;

import com.upc.cargasinestres.CargaSinEstres.Contract.model.Contract;
import com.upc.cargasinestres.CargaSinEstres.Contract.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class ContractsController {

    private final ContractService contractService;

    public ContractsController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/blockchain/version")
    public String getClientVersion() throws IOException {
        return contractService.getClientVersion();
    }

    @PostMapping("/deploy")
    public ResponseEntity<String> deployContract(@RequestBody Contract contractData) {
        try {
            // Delegamos al servicio la tarea de desplegar el contrato
            String contractAddress = contractService.deployContract(contractData);
            return ResponseEntity.ok("Contrato desplegado en: " + contractAddress);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al desplegar el contrato");
        }
    }
}
