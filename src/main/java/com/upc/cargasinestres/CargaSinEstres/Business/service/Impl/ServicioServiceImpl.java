package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.ServicioValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.response.CompanyResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.request.ServicioRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.response.ServicioResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Servicio;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IServicioRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IServicioService;
import org.modelmapper.ModelMapper;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Qualifier("servicioServiceImpl")
public class ServicioServiceImpl implements IServicioService {
    private final IServicioRepository servicioRepository;

    private final ModelMapper modelMapper;

    public ServicioServiceImpl(IServicioRepository servicioRepository, ModelMapper modelMapper) {
        this.servicioRepository = servicioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServicioResponseDto createServicio(ServicioRequestDto servicioRequestDto){

        //validation
        String nombreServicio = servicioRequestDto.getName().toLowerCase();
        if (servicioRepository.findByName(nombreServicio) != null) {
            throw new ValidationException("Ya existe un servicio con el nombre proporcionado"); // Error 400
        }
        ServicioValidation.ValidateServicio(servicioRequestDto);

        //create
        var newService = modelMapper.map(servicioRequestDto, Servicio.class);
        newService.setName(nombreServicio);
        var createdService = servicioRepository.save(newService);
        return modelMapper.map(createdService, ServicioResponseDto.class);
    }

    @Override
    public List<ServicioResponseDto> getAllServices(){
        var servicios = servicioRepository.findAll();

        return servicios.stream()
                .map(servicio -> modelMapper.map(servicio, ServicioResponseDto.class))
                .toList();
    }
}
