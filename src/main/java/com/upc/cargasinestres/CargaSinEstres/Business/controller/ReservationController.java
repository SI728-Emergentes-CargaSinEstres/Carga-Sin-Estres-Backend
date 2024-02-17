package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDtoV2;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDtoV3;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.response.ReservationResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Booking history controller for the management of booking histories of the API
 * Provides the methods to manage the bookings
 * @author Grupo1
 * @version 1.0
 */
@Tag(name="Reservation Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class ReservationController {

    private final IReservationService bookingHistoryService;
    private final IChatService chatService;

    /**
     * Class constructor
     * @param bookingHistoryService The service for handling booking history operations.
     */
    public ReservationController(IReservationService bookingHistoryService, IChatService chatService) {
        this.bookingHistoryService = bookingHistoryService;
        this.chatService = chatService;
    }

    /**
     * Creates a new booking history record.
     *
     * @param customerId              The ID of the client associated with the booking history.
     * @param companyId             The ID of the company associated with the booking history.
     * @param reservationRequestDto The data for creating the booking history.
     * @return A ResponseEntity containing the created BookingHistoryResponseDtoV2 and HttpStatus.CREATED.
     */
    @Operation(summary = "Create a Reservation")
    @PostMapping("/reservation")
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestParam(name = "customerId") Long customerId, @RequestParam(name = "idCompany") Long companyId, @RequestBody ReservationRequestDto reservationRequestDto) {
        var res = bookingHistoryService.createReservation(customerId, companyId, reservationRequestDto);

        var chat = chatService.createChat(res.getId(), null); //revision

        if (chat == null) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of booking history records by client ID.
     *
     * @param id The ID of the client.
     * @return A ResponseEntity containing the list of BookingHistoryResponseDtoV2 and HttpStatus.OK.
     */
    @Operation(summary = "Obtain a list of Booking History by client Id")
    @GetMapping("/reservation/customer/{id}")
    public ResponseEntity<List<ReservationResponseDto>> getBookingHistoryByClientId(@PathVariable(name="id") Long id){
        var res = bookingHistoryService.getReservationByCustomerId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Retrieves a list of booking history records by company ID.
     *
     * @param id The ID of the company.
     * @return A ResponseEntity containing the list of BookingHistoryResponseDtoV2 and HttpStatus.OK.
     */
    @Operation(summary = "Obtain a list of Booking History by company Id")
    @GetMapping("/bookingHistory/company/{id}")
    public ResponseEntity<List<ReservationResponseDto>> getBookingHistoryByCompanyId(@PathVariable(name="id") Long id){
        var res = bookingHistoryService.getReservationByCompanyId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    /**
     * Updates the payment field of a specific booking history.
     *
     * @param bookingHistoryId The ID of the booking history to be updated.
     * @param price The data for updating the booking history.
     * @return The response of the updated booking history.
     */
    @Operation(summary = "Update the payment of a Booking History")
    @PatchMapping("/bookingHistory/{id}/payment")
    public ResponseEntity<ReservationResponseDto> updateBookingHistoryPayment(@PathVariable(name = "id") Long bookingHistoryId, Long price) {
        var res = bookingHistoryService.updateReservationPrice(bookingHistoryId, price);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Updates the status field of a specific booking history.
     *
     * @param bookingHistoryId The ID of the booking history to be updated.
     * @param reservationRequestDtoV3 The data status for updating the booking history.
     * @return The response of the updated booking history.
     */
    @Operation(summary = "Update the status of a Booking History")
    @PatchMapping("/bookingHistory/{id}/status")
    public ResponseEntity<ReservationResponseDto> updateBookingHistoryStatus(@PathVariable(name = "id") Long bookingHistoryId, @RequestBody ReservationRequestDtoV3 reservationRequestDtoV3) {
        var res = bookingHistoryService.updateReservationStatus(bookingHistoryId, reservationRequestDtoV3);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}

