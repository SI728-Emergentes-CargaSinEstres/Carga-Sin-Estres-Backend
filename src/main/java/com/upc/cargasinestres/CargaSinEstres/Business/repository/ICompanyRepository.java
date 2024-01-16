package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Client;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * The ICompanyRepository interface extends JpaRepository for CRUD operations on Company entities.
 * It provides methods for querying companies based on name, contact number, and other criteria.
 * @author Grupo1
 * @version 1.0
 * */
public interface ICompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Retrieves an Optional<Company> based on the provided name and contact number.
     *
     * @param name The name of the company.
     * @param numeroContacto The contact number of the company.
     * @return An Optional containing the company if found, otherwise an empty Optional.
     */
    Optional<Company> findByNameAndNumeroContacto(String name, String numeroContacto);

    Optional<Company> findCompanyByEmailAndPassword(String email, String password); //para login
    Company findByEmailAndPassword(String email, String password);

}
