package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
@Table(name="companies")
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
     * The address of the company.
     */
    @Column(name = "direction", nullable = false)
    private String direction;

    /**
     * The contact number of the company.
     */
    @Column(name = "TIC", nullable = false)
    private String TIC;

    /**
     * The contact number of the company.
     */
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    /**
     * The password of the company.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The description of the company
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The rating of the company
     */
    @OneToMany(mappedBy = "rating", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    /**
     * The services of the company
     */
    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    private List<Servicio> servicios;

    @OneToOne
    @JoinColumn(name="idCompany", nullable = true, foreignKey = @ForeignKey(name="FK_membership_company"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Membership membership; //cambiar a conexion por id

}
