package com.upc.cargasinestres.CargaSinEstres.ReservationManagement.domain.service;

import com.upc.cargasinestres.CargaSinEstres.ReservationManagement.domain.dto.Ubigeo.UbigeoDto;

import java.util.List;

public interface IUbigeoService {

    public abstract List<String> getDepartamentos();

    public abstract List<String> getProvincias(String departamento);

    public abstract List<UbigeoDto> getDistritos(String provincia);

    public abstract List<String> getLocationByIdUbigeo(Long idUbigeo);
}
