package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.shared;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.dto.Servicio.response.ServicioResponseDto;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.entity.Rating;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.entity.Servicio;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.repository.IServicioRepository;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.model.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyQueryServiceImpl implements CompanyQueryService {

    private final IServicioRepository servicioRepository;

    public CompanyQueryServiceImpl(IServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public int calculateAverageRating(List<Rating> ratings) {
        if (ratings.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getStars();
        }
        return sum / ratings.size();
    }

    @Override
    public List<Servicio> findServicesByCompany(Company company) {
       return List.of();
    }

    public List<Long> findAllIdsServices() {
        var servicios = servicioRepository.findAll();
        return servicios.stream()
                .map(Servicio::getId) // Obtener el ID de cada servicio
                .toList();            //retorna uan lista con todos los id
    }

    @Override
    public List<Servicio> findAllServicerById(List<Long> idsList) {
        return servicioRepository.findAllById(idsList);
    }

}