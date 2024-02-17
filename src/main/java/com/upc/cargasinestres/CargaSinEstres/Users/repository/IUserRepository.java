package com.upc.cargasinestres.CargaSinEstres.Users.repository;

import com.upc.cargasinestres.CargaSinEstres.Users.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface for the user repository
 * @author Grupo1
 * @version 1.0
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    /**
     * Searchs a user by its email or username
     * @param email Email
     * @param username Username
     * @return Usuario encontrado
     */
    Optional<User> findByEmailOrUsername(String email, String username);

    /**
     * Verifies if a user exists by its email
     * @param email Email
     * @return True si existe, false si no
     */
    boolean existsByEmail(String email);

    /**
     * Verifies if a user exists by its username
     * @param username Username
     * @return True si existe, false si no
     */
    boolean existsByUsername(String username);
}