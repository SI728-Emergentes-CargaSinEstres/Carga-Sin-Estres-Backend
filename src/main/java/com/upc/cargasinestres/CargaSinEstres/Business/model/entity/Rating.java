package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stars", nullable = false)
    private int stars;

    @ManyToOne
    @JoinColumn(name="idCompany", nullable = false, foreignKey = @ForeignKey(name="FK_company_rating"))
    private Company company;
}
