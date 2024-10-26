package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.shared;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.entity.Rating;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.entity.Servicio;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.model.entity.Company;

import java.util.List;

public interface CompanyQueryService {

    public abstract int calculateAverageRating(List<Rating> ratings);

    public abstract List<Servicio> findServicesByCompany(Company company);

    //todala implementacion lo que se usa en el bounded context de usur managemt para validar
    //el registro de una company

    //obtener todos los ids de los objetos Service creados
    public abstract List<Long> findAllIdsServices();

    //obtener objetos Service de acuerdo a sus ids
    public abstract List<Servicio> findAllServicerById(List<Long> idsList);


}
