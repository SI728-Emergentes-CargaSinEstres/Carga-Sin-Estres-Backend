package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.shared;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.entity.Rating;
import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.entity.Servicio;
import com.upc.cargasinestres.CargaSinEstres.UsersManagement.model.entity.Company;

import java.util.List;

public interface CompanyQueryService {

    public abstract int calculateAverageRating(List<Rating> ratings);

    public abstract List<Servicio> findServicesByCompany(Company company);
}
