package com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.interfaces.repository;

import com.upc.cargasinestres.CargaSinEstres.ContractManagement.domain.entity.AdditionalWaitingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAdditionalWaitingTimeRepository extends JpaRepository<AdditionalWaitingTime, Long> {
    Optional<AdditionalWaitingTime> findByCustomerId(Long customerId);
    List<AdditionalWaitingTime> findByNextReservationDateLessThanEqual(LocalDate date);
    Boolean existsByCustomerId(Long customerId);
}
