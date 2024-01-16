package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the Company entity for CSE. The table name is companies. And the columns are:
 * <ul>
 *     <li>id - The id of the company</li>
 *     <li>name - The name of the company</li>
 *     <li>photo - The photo of the company</li>
 *     <li>email - The email of the company</li>
 *     <li>direccion - The adress of the company</li>
 *     <li>numeroContacto - The contact number of the company</li>
 *     <li>password - The password of the company</li>
 *     <li>transporte - If the company has transporte</li>
 *     <li>carga - If the company has carga</li>
 *     <li>embalaje - If the company has embalaje</li>
 *     <li>montaje - If the company has montaje</li>
 *     <li>desmontaje - If the company has desmontaje</li>
 *     <li>description - The description of the company</li>
 *     <li>userType - The userType of the company</li>
 * </ul>
 * @author Grupo1
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="company")
public class Company {
    /**
     * The id of the company.
     * This is a primary key.
     * This id is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the company.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The photo of the company.
     */
    @Column(name = "photo", nullable = false)
    private String photo;

    /**
     * The email of the company.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * The adress of the company.
     */
    @Column(name = "direccion", nullable = false)
    private String direccion;

    /**
     * The contact number of the company.
     */
    @Column(name = "numeroContacto", nullable = false)
    private String numeroContacto;

    /**
     * The password of the company.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * If the company has transporte.
     */
    @Column(name = "transporte", nullable = false)
    private boolean transporte;

    /**
     * If the company has carga.
     */
    @Column(name = "carga", nullable = false)
    private boolean carga;

    /**
     * If the company has embalaje.
     */
    @Column(name = "embalaje", nullable = false)
    private boolean embalaje;

    /**
     * If the company has montaje
     */
    @Column(name = "montaje", nullable = false)
    private boolean montaje;

    /**
     * If the company has desmontaje
     */
    @Column(name = "desmontaje", nullable = false)
    private boolean desmontaje;

    /**
     * The average rating given to the company via reviews
     */
    @Column(name = "averageRating", nullable = false)
    private int averageRating;

    /**
     * The description of the company
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The user type of company
     */
    @Column(name = "userType", nullable = false)
    private String userType;


}
