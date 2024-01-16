package com.upc.cargasinestres.CargaSinEstres.Users.repository;

import com.upc.cargasinestres.CargaSinEstres.Users.model.Entity.Role;
import com.upc.cargasinestres.CargaSinEstres.Users.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

/**
 * Interface that allows to access the database and make queries to the table "roles"
 * @author Grupo1
 * @version 1.0
 */

public interface IRoleRepository extends JpaRepository<Role, Long> {
    /**
     * Searchs a role by its name
     * @param name Nombre del rol
     * @return Rol encontrado (si existe)
     */
    Optional<Role> findByName(ERole name);

    /**
     * Verifies if a role exists by its name
     * @param name Nombre del rol
     * @return true si existe, false si no
     */
    boolean existsByName(ERole name);
}
