package com.upc.cargasinestres.CargaSinEstres.CompanyManagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "timeblocks")
public class Timeblock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="start_time", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @Column(name="end_time", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @Column(name = "company_id", nullable = false)
    private Long companyId;
}
