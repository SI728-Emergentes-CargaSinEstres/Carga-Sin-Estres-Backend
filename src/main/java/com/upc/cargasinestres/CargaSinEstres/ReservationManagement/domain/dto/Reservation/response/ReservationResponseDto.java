package com.upc.cargasinestres.CargaSinEstres.ReservationManagement.domain.dto.Reservation.response;

import com.upc.cargasinestres.CargaSinEstres.UsersManagement.domain.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * This class represents the response of the booking history
 * @author Grupo1
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {
    private Long id;
    private LocalDate startDate;
    private String startTime;
    private Long ubigeoOrigin;
    private String originAddress;
    private Long ubigeoDestination;
    private String destinationAddress;
    private String endDate;
    private String endTime;
    private float price;
    private String status;
    private String services;
    private Long chatId;
    private Customer customer;
    private String companyName;
}
