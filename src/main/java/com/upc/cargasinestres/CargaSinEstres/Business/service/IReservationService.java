package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.response.ReservationResponseDto;

import java.util.List;

public interface IReservationService {

    //create reservation
    public abstract ReservationResponseDto createReservation(Long clientId, Long companyId, ReservationRequestDto reservationRequestDto);

    //get all reservation for a client by id
    public abstract List<ReservationResponseDto> getReservationByCustomerId(Long customerId);

    //get all reservation for a company by id
    public abstract List<ReservationResponseDto> getReservationByCompanyId(Long companyId);

    //update reservation price
    ReservationResponseDto updateReservationPrice(Long reservationId, float price);

    //update reservation status
    ReservationResponseDto updateReservationStatus(Long reservationId, String status);

    //update bookingHistory chat

}