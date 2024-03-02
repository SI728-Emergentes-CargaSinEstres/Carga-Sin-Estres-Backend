package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The ICompanyRepository interface extends JpaRepository for CRUD operations on Company entities.
 * It provides methods for querying companies based on name, contact number, and other criteria.
 * @author Grupo1
 * @version 1.0
 * */
public interface ICompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Retrieves an Optional<Company> based on the provided name and contact number. This is used to avoid duplicate companies with the same TIC and name
     *
     * @param name The name of the company.
     * @param TIC The contact number of the company.
     * @return An Optional containing the company if found, otherwise an empty Optional.
     */
    Optional<Company> findByNameAndTIC(String name, String TIC);

    /**
     * Retrieves an Optional<Company> based on the provided email and contact number. This is used to
     *
     * @param email The email of the company.
     * @param password The contact password used by the company account.
     * @return An Optional containing the company info if account is found for login, otherwise an empty Optional.
     */
    Optional<Company> findByEmailAndPassword(String email, String password);

    /**
     * Retrieves a Company based on the provided company ID.
     *
     * @param id The ID of the company.
     * @return The company if found, otherwise null.
     */
    Company findCompanyById(Long id);

    /**
     * Retrieves an Optional<Company> based on the provided email. This is used to avoid duplicate companies with the same email
     *
     * @param email The email of the company.
     * @return An Optional containing the company if found, otherwise an empty Optional.
     */
    Optional<Company> findByEmail(String email);

    /**
     * Retrieves an Optional<Company> based on the provided phone number. This is used to avoid duplicate companies with the same phone number
     *
     * @param phoneNumber The phone number of the company.
     * @return An Optional containing the company if found, otherwise an empty Optional.
     */
    Optional<Company> findByPhoneNumber(String phoneNumber);

}
