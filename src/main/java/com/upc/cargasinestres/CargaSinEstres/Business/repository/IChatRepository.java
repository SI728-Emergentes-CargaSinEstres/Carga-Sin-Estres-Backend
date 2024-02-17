package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Chat entity This extends JpaRepository, which provides the methods for CRUD operations
 * The methods are implemented by Spring Data JPA The methods are:
 * <ul>
 *     <li> findByBookingHistoryId - returns a list of ChatResponseDto by bookingHistoryId </li>
 * </ul>
 * @author Grupo1
 * @version 1.0
 */
@Repository
public interface IChatRepository extends JpaRepository<Chat, Long> {

    /**
     * Returns a list of ChatResponseDto by reservationId
     * @param reservationId - id of the booking history
     * @return a list of ChatResponseDto
     */
    Chat findByReservationId(Long reservationId);

}
