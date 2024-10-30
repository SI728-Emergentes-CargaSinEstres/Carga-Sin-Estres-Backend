package com.upc.cargasinestres.CargaSinEstres.ReservationManagement.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ubigeos")
public class Ubigeo {
    @Id
    @Column(name = "id_ubigeo", nullable = false)
    private Long idUbigeo;

    @Column(name = "ubigeo_reniec", length = 10)
    private String ubigeoReniec;

    @Column(name = "ubigeo_inei", length = 10)
    private String ubigeoInei;

    @Column(name = "departamento_inei", length = 10)
    private String departamentoInei;

    @Column(name = "departamento", length = 50)
    private String departamento;

    @Column(name = "provincia_inei", length = 10)
    private String provinciaInei;

    @Column(name = "provincia", length = 50)
    private String provincia;

    @Column(name = "distrito", length = 50)
    private String distrito;

    @Column(name = "region", length = 50)
    private String region;

    @Column(name = "macroregion_inei", length = 50)
    private String macroregionInei;

    @Column(name = "macroregion_minsa", length = 50)
    private String macroregionMinsa;

    @Column(name = "iso_3166_2", length = 50)
    private String iso31662;

    @Column(name = "fips")
    private Long fips;

    @Column(name = "superficie", length = 50)
    private String superficie;

    @Column(name = "altitud", length = 10)
    private String altitud;

    @Column(name = "latitud", length = 10)
    private String latitud;

    @Column(name = "longitud", length = 50)
    private String longitud;

    @Column(name = "frontera", length = 50)
    private String frontera;
}