package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.request.ServicioRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.response.ServicioResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IServicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Service Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class ServicioController {
    private final IServicioService serviceService;

    public ServicioController(IServicioService serviceService){
        this.serviceService = serviceService;
    }

    @Operation(summary = "Create a Service")
    @PostMapping("/services")
    public ResponseEntity<ServicioResponseDto> createServicio(@RequestBody ServicioRequestDto servicioRequestDto) {
        var res = serviceService.createServicio(servicioRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
