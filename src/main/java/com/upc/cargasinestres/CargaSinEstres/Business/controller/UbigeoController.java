package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.response.ServicioResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Ubigeo.UbigeoDto;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IUbigeoRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IServicioService;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IUbigeoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Ubigeo Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class UbigeoController {

    private final IUbigeoService ubigeoService;

    public UbigeoController(IUbigeoService ubigeoService){
        this.ubigeoService = ubigeoService;
    }


    @GetMapping("/departamentos")
    public ResponseEntity<List<String>> getAllDepartamentos() {
        List<String> departamentos = ubigeoService.getDepartamentos();
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/provincias/{departamento}")
    public ResponseEntity<List<String>> getProvincias(@PathVariable String departamento) {
        List<String> provincias = ubigeoService.getProvincias(departamento);
        return ResponseEntity.ok(provincias);
    }

    @GetMapping("/distritos/{provincia}")
    public ResponseEntity<List<UbigeoDto>> getDistritos(@PathVariable String provincia) {
        List<UbigeoDto> distritos = ubigeoService.getDistritos(provincia);
        return ResponseEntity.ok(distritos);
    }

    @GetMapping("/location/{idUbigeo}")
    public List<String> getLocationByIdUbigeo(@PathVariable Long idUbigeo) {
        return ubigeoService.getLocationByIdUbigeo(idUbigeo);
    }

}
