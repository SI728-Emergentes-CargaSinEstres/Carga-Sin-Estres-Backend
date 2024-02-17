package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Chat;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IServiceRepository extends JpaRepository<Service, Long> {
    /**
     * Retrieves a list of services based on the service name.
     * @param name The name of the service.
     * @return A List of Service records associated with the specified service name.
     */
    Service findByServiceName(String name);
}
