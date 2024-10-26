package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.interfaces.service;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.dto.Servicio.request.ServicioRequestDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.dto.Servicio.response.ServicioResponseDto;

import java.util.List;

public interface IServicioService {
    //create service
    public abstract ServicioResponseDto createServicio(ServicioRequestDto servicioRequestDto);

    //get services
    public abstract List<ServicioResponseDto> getAllServices();
}