package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.repository;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query; // Para la anotación @Query
import org.springframework.data.repository.query.Param; // Para @Param
import java.util.Optional; // Para Optional

import java.util.Optional;

@Repository
public interface IContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findTopByReservationIdOrderByIdDesc(Long reservationId);
} //4 y 26
