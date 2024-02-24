package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "company_service", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "service_id"), // Columna que hace referencia a la entidad actual (Servicio)
            inverseJoinColumns = @JoinColumn(name = "company_id") // Columna que hace referencia a la entidad relacionada (Company)
    )
    private List<Company> companies;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservation_service", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "service_id"), // Columna que hace referencia a la entidad actual (Servicio)
            inverseJoinColumns = @JoinColumn(name = "reservation_id") // Columna que hace referencia a la entidad relacionada (Company)
    )
    private List<Reservation> reservations;
}
