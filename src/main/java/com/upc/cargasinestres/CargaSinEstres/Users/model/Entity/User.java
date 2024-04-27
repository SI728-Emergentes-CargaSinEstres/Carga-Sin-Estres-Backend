package com.upc.cargasinestres.CargaSinEstres.Users.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * The User Entity represents the data of a user in the database.
 * The fields of the Role Entity are:
 * - userId: the id of the user.
 * - fullName: the full name of the user.
 * - username: the username of the user.
 * - email: the email of the user.
 * - password: the password of the user.
 * - roles: the roles of the user (ADMIN, USER).
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    /**
     * The userId is the id of the user.
     * The userId is a Long value.
     * - The userId is the primary key of the user.
     * - The userId is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * The fullName is the full name of the user.
     * The fullName is a String value.
     */
    @Column(nullable = false)
    private String fullName;

    /**
     * The username is the username of the user.
     * The username is a String value.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * The email is the email of the user.
     * The email is a String value.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The password is the password of the user.
     * The password is a String value.
     */
    @Column(nullable = false)
    private String password;

    /**
     * -Info: MANY "users" can have MANY "roles"
     * -JoinTable: The join table is the table that contains the foreign keys of both tables.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}