package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Ubigeo.UbigeoDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Ubigeo;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IUbigeoRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IUbigeoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UbigeoServiceImpl implements IUbigeoService {

    private final IUbigeoRepository ubigeoRepository;

    private final ModelMapper modelMapper;

    public UbigeoServiceImpl(IUbigeoRepository ubigeoRepository, ModelMapper modelMapper) {
        this.ubigeoRepository = ubigeoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> getDepartamentos() {
        return ubigeoRepository.findAll()
                .stream()
                .map(Ubigeo::getDepartamento)
                .distinct()
                .collect(Collectors.toList());

    }

    @Override
    public List<String> getProvincias(String departamento) {
        return ubigeoRepository.findAll()
                .stream()
                .filter(ubigeo -> ubigeo.getDepartamento().equals(departamento))
                .map(Ubigeo::getProvincia)
                .distinct()
                .collect(Collectors.toList());

    }

    @Override
    public List<UbigeoDto> getDistritos(String provincia) {
        return ubigeoRepository.findAll()
                .stream()
                .filter(ubigeo -> ubigeo.getProvincia().equals(provincia))
                .map(ubigeo -> new UbigeoDto(ubigeo.getIdUbigeo(), ubigeo.getDistrito()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getLocationByIdUbigeo(Long idUbigeo) {
        return ubigeoRepository.findAll()
                .stream()
                .filter(ubigeo -> ubigeo.getIdUbigeo().equals(idUbigeo)) // Filtra por idUbigeo
                .map(ubigeo -> List.of(ubigeo.getDepartamento(), ubigeo.getProvincia(), ubigeo.getDistrito())) // Mapea a la lista de departamento, provincia y distrito
                .findFirst() // Obtiene el primer resultado (debería haber solo uno)
                .orElseThrow(() -> new RuntimeException("Ubigeo no encontrado para el id: " + idUbigeo)); // Lanza excepción si no existe
    }
}
