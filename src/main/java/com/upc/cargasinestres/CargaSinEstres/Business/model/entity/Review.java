package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the Review entity for CSE. The table name is reviews. And the columns are:
 * <ul>
 *     <li>id - The id of the review</li>
 *     <li>rating - The rating of the review</li>
 *     <li>comment - The comment of the review</li>
 *     <li>company - The company that the review belongs to</li>
 * </ul>
 * @author Grupo1
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    /**
     * The id of the review.
     * This is a primary key.
     * This id is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The rating of the review.
     */
    @Column(name = "rating", nullable = false)
    private int rating;

    /**
     * The comment of the review.
     */
    @Column(name = "comment", nullable = false)
    private String comment;

    /**
     * The company that the review belongs to.
     * This is a foreign key.
     * This is a many to one relationship.
     */
    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false, foreignKey = @ForeignKey(name = "FK_COMPANY_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Company company;

}
