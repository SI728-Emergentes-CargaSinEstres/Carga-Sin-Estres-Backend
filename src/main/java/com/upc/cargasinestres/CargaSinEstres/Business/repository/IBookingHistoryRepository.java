package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The IBookingHistoryRepository interface extends JpaRepository for CRUD operations on BookingHistory entities.
 * It provides methods for querying booking history records based on client ID, company ID, and other criteria.
 * @author Grupo1
 * @version 1.0
 * */
public interface IBookingHistoryRepository extends JpaRepository<BookingHistory, Long>{

    /**
     * Retrieves a list of booking history records based on the client ID.
     *
     * @param clientId The ID of the client.
     * @return A List of BookingHistory records associated with the specified client.
     */
    List<BookingHistory> findByClientId(Long clientId);

    /**
     * Retrieves a list of booking history records based on the company ID.
     *
     * @param companyId The ID of the company.
     * @return A List of BookingHistory records associated with the specified company.
     */
    List<BookingHistory> findByCompanyId(Long companyId);

    //Boolean existsByPickupAddressAndDestinationAddress(String pickupAddress, String destinationAddress);



}
