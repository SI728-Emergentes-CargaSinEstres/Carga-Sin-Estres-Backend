package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.service;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Servicio.request.ServicioRequestDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Servicio.response.ServicioResponseDto;

import java.util.List;

public interface IServicioService {
    //create service
    public abstract ServicioResponseDto createServicio(ServicioRequestDto servicioRequestDto);

    //get services
    public abstract List<ServicioResponseDto> getAllServices();
}