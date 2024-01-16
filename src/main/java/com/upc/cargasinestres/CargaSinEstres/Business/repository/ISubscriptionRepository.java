package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Subscription entities.
 * @author Grupo1
 * @version 1.0
 * */
@Repository
public interface ISubscriptionRepository extends JpaRepository<Subscription, Long> {
    /**
     * Retrieve a subscription by the specified company ID.
     * @param companyId The unique identifier of the company associated with the subscription.
     * @return An Optional containing the Subscription entity, or empty if not found.
     */
    Optional<Subscription> findByCompanyId(Long companyId);

    /**
     * Check if a subscription exists for the specified company ID.
     * @param companyId The unique identifier of the company associated with the subscription.
     * @return true if a subscription exists, false otherwise.
     */
    boolean existsByCompanyId(Long companyId);

}
