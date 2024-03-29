package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.response.ReservationResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Reservation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IReservationService {

    //create reservation
    public abstract ReservationResponseDto createReservation(Long clientId, Long companyId, ReservationRequestDto reservationRequestDto);

    //get all reservation for a client by id
    public abstract List<ReservationResponseDto> getReservationByCustomerId(Long customerId);

    //get all reservation for a company by id
    public abstract List<ReservationResponseDto> getReservationByCompanyId(Long companyId);

    // get all reservations for a company by id and status
    public abstract List<ReservationResponseDto> getReservationByCompanyIdAndStatus(Long companyId, String status);

    //update reservation price
    //ReservationResponseDto updateReservationPrice(Long reservationId, float price);

    //update reservation status
    ReservationResponseDto updateReservationStatus(Long reservationId, String status);

    ReservationResponseDto updateReservationEndDateAndEndTime(Long reservationId, LocalDate endDate, String endTime);

    ReservationResponseDto updateReservationChatId(Long reservationId, Long chatId);

    Reservation getById(Long resId);

    ReservationResponseDto updateReservationPriceStartDateStartTime(Long reservationId, float price, LocalDate startDate, String startTime, String status);
}
