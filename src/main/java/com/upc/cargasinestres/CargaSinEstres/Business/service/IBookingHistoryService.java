package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request.BookingHistoryRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request.BookingHistoryRequestDtoV2;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request.BookingHistoryRequestDtoV3;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.response.BookingHistoryResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.response.BookingHistoryResponseDtoV2;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.request.ChatRequestDto;

import java.util.List;

public interface IBookingHistoryService {

    //create bookingHistory
    public abstract BookingHistoryResponseDto createBookingHistory(Long clientId, Long companyId, BookingHistoryRequestDto bookingHistoryRequestDto);

    //get all bookingHistory for a client by Id
    public abstract List<BookingHistoryResponseDtoV2> getBookingHistoryByClientId(Long id);

    //get all bookingHistory for a company by Id
    public abstract List<BookingHistoryResponseDtoV2> getBookingHistoryByCompanyId(Long id);

    //update bookingHistory payment
    BookingHistoryResponseDtoV2 updateBookingHistoryPayment(Long bookingHistoryId, BookingHistoryRequestDtoV2 bookingHistoryRequestDto);

    //update bookingHistory status
    BookingHistoryResponseDtoV2 updateBookingHistoryStatus(Long bookingHistoryId, BookingHistoryRequestDtoV3 bookingHistoryRequestDto);

    //update bookingHistory chat

}
