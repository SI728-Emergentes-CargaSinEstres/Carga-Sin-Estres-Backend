package com.upc.cargasinestres.CargaSinEstres.ReservationManagement.domain.repository;

import com.upc.cargasinestres.CargaSinEstres.ReservationManagement.domain.entity.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUbigeoRepository extends JpaRepository<Ubigeo, Long> {

}

