package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.repository;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByReservationId(Long reservationId);
} //4 y 26
