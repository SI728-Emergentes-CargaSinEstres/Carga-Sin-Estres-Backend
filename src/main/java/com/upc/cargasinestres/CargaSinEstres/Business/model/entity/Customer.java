package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the Client entity for CSE. The table name is clients. And the columns are:
 * <ul>
 *     <li>id - The id of the client</li>
 *     <li>name - The name of the client</li>
 *     <li>apellidoMaterno - The last name of the client</li>
 *     <li>apellidoPaterno - The last name of the client</li>
 *     <li>celular - The phone number of the client</li>
 *     <li>direccion - The address of the client</li>
 *     <li>email - The email of the client</li>
 *     <li>password - The password of the client</li>
 *     <li>userType - The userType of the client</li>
 * </ul>
 * @author Grupo1
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    /**
     * The id of the client.
     * This is a primary key.
     * This id is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the client.
     */
    @Column(name = "name", nullable = false)
    private String first_name;

    /**
     * The last name of the client.
     */
    @Column(name = "last_name", nullable = false)
    private String last_name;

    /**
     *  The phone number of the client.
     */
    @Column(name = "phone_number", length = 9, nullable = false)
    private String phone_number;

    /**
     * The email of the client.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * The password of the client.
     */
    @Column(name = "password", nullable = false)
    private String password;

}