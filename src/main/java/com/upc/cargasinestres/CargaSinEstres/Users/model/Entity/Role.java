package com.upc.cargasinestres.CargaSinEstres.Users.model.Entity;

import com.upc.cargasinestres.CargaSinEstres.Users.model.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Role Entity represents the data of a role in the database.
 * The fields of the Role Entity are:
 * - roleId: Long
 * - name: ERole (enum -> ADMIN, USER)
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    /**
     * The roleId is the id of the role.
     * The roleId is a Long value.
     * - The roleId is the primary key of the role.
     * - The roleId is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    /**
     *  The name is the name of the role.
     *  The name is a ERole value.
     *  - The name is not null.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ERole name;
}