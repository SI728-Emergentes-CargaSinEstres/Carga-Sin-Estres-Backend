package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * The IClientRepository interface extends JpaRepository for CRUD operations on Client entities.
 * It provides methods for querying clients based on email, password, and other criteria.
 * @author Grupo1
 * @version 1.0
 * */
public interface IClientRepository extends JpaRepository<Client, Long> {
    /**
     * Retrieves an Optional<Client> based on the provided email and password.
     *
     * @param email The email of the client.
     * @param password The password of the client.
     * @return An Optional containing the client if found, otherwise an empty Optional.
     */
    @Query("SELECT c FROM Client c WHERE c.email = :email AND c.password = :password")
    Optional<Client> existsClient(@Param("email") String email, @Param("password") String password);

    /**
     * Retrieves a list of clients based on the provided email and password.
     *
     * @param email The email of the client.
     * @param password The password of the client.
     * @return A List of clients matching the specified email and password.
     */
    Client findByEmailAndPassword(String email, String password);
}
