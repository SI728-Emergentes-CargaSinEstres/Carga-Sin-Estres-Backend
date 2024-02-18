package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.request.ServicioRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.response.ServicioResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Servicio;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IReservationRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IServicioRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IServicioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("servicioServiceImpl")
public class ServicioServiceImpl implements IServicioService {
    private final IServicioRepository servicioRepository;
    private final ICompanyRepository companyRepository;
    private final IReservationRepository reservationRepository;

    private final ModelMapper modelMapper;

    public ServicioServiceImpl(IServicioRepository servicioRepository, ICompanyRepository companyRepository, IReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.servicioRepository = servicioRepository;
        this.companyRepository = companyRepository;
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServicioResponseDto createServicio(ServicioRequestDto servicioRequestDto){
        var newService = modelMapper.map(servicioRequestDto, Servicio.class);
        var createdService = servicioRepository.save(newService);
        return modelMapper.map(createdService, ServicioResponseDto.class);
    }

    // :D
}
