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
     * The TIC of the company.
     */
    @Column(name = "TIC", nullable = false)
    private String TIC;

    /**
     * The address of the company.
     */
    @Column(name = "direction", nullable = false)
    private String direction;

    /**
     * The email of the company.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * The contact number of the company.
     */
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    /**
     * The password of the company.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The photo of the company.
     */
    @Column(name = "logo", nullable = false)
    private String logo;

    /**
     * The description of the company
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The rating of the company
     */
    @OneToMany(mappedBy="company")
    private List<Rating> ratings;

    @Column(name="idMembership")
    private Long membershipId; //cambiar a conexion por id

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "company_servicio", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "company_id"), // Columna que hace referencia a la entidad actual (Company)
            inverseJoinColumns = @JoinColumn(name = "servicio_id") // Columna que hace referencia a la entidad relacionada (Servicio)
    )
    private List<Servicio> servicios;
}
