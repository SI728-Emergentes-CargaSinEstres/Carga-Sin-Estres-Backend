package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Ubigeo.UbigeoDto;

import java.util.List;

public interface IUbigeoService {

    public abstract List<String> getDepartamentos();

    public abstract List<String> getProvincias(String departamento);

    public abstract List<UbigeoDto> getDistritos(String provincia);
}
