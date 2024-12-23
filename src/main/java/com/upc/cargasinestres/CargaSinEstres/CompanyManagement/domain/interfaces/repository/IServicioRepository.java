package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.interfaces.repository;

import com.upc.cargasinestres.CargaSinEstres.CompanyManagement.domain.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServicioRepository extends JpaRepository<Servicio, Long> {
    /**
     * Retrieves a list of services based on the service name.
     * @param name The name of the service.
     * @return A List of Service records associated with the specified service name.
     */
    Servicio findByName(String name);
}
