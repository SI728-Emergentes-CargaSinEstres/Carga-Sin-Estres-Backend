package com.upc.cargasinestres.CargaSinEstres.Business.controller;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request.BookingHistoryRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request.BookingHistoryRequestDtoV2;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request.BookingHistoryRequestDtoV3;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.response.BookingHistoryResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.response.BookingHistoryResponseDtoV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IBookingHistoryService;
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
@Tag(name="BookingHistory Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class BookingHistoryController {

    private final IBookingHistoryService bookingHistoryService;

    /**
     * Class constructor
     * @param bookingHistoryService The service for handling booking history operations.
     */
    public BookingHistoryController(IBookingHistoryService bookingHistoryService) {
        this.bookingHistoryService = bookingHistoryService;
    }

    /**
     * Creates a new booking history record.
     *
     * @param clientId              The ID of the client associated with the booking history.
     * @param companyId             The ID of the company associated with the booking history.
     * @param bookingHistoryRequestDto The data for creating the booking history.
     * @return A ResponseEntity containing the created BookingHistoryResponseDtoV2 and HttpStatus.CREATED.
     */
    @Operation(summary = "Create a Booking History")
    @PostMapping("/bookingHistory")
    public ResponseEntity<BookingHistoryResponseDto> createBookingHistory(@RequestParam(name = "idClient") Long clientId, @RequestParam(name = "idCompany") Long companyId, @RequestBody BookingHistoryRequestDto bookingHistoryRequestDto) {
        var res = bookingHistoryService.createBookingHistory(clientId, companyId, bookingHistoryRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of booking history records by client ID.
     *
     * @param id The ID of the client.
     * @return A ResponseEntity containing the list of BookingHistoryResponseDtoV2 and HttpStatus.OK.
     */
    @Operation(summary = "Obtain a list of Booking History by client Id")
    @GetMapping("/bookingHistory/client/{id}")
    public ResponseEntity<List<BookingHistoryResponseDtoV2>> getBookingHistoryByClientId(@PathVariable(name="id") Long id){
        var res = bookingHistoryService.getBookingHistoryByClientId(id);
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
    public ResponseEntity<List<BookingHistoryResponseDtoV2>> getBookingHistoryByCompanyId(@PathVariable(name="id") Long id){
        var res = bookingHistoryService.getBookingHistoryByCompanyId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    /**
     * Updates the payment field of a specific booking history.
     *
     * @param bookingHistoryId The ID of the booking history to be updated.
     * @param bookingHistoryRequestDtoV2 The data for updating the booking history.
     * @return The response of the updated booking history.
     */
    @Operation(summary = "Update the payment of a Booking History")
    @PatchMapping("/bookingHistory/{id}/payment")
    public ResponseEntity<BookingHistoryResponseDtoV2> updateBookingHistoryPayment(@PathVariable(name = "id") Long bookingHistoryId, @RequestBody BookingHistoryRequestDtoV2 bookingHistoryRequestDtoV2) {
        var res = bookingHistoryService.updateBookingHistoryPayment(bookingHistoryId, bookingHistoryRequestDtoV2);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Updates the status field of a specific booking history.
     *
     * @param bookingHistoryId The ID of the booking history to be updated.
     * @param bookingHistoryRequestDtoV3 The data status for updating the booking history.
     * @return The response of the updated booking history.
     */
    @Operation(summary = "Update the status of a Booking History")
    @PatchMapping("/bookingHistory/{id}/status")
    public ResponseEntity<BookingHistoryResponseDtoV2> updateBookingHistoryStatus(@PathVariable(name = "id") Long bookingHistoryId, @RequestBody BookingHistoryRequestDtoV3 bookingHistoryRequestDtoV3) {
        var res = bookingHistoryService.updateBookingHistoryStatus(bookingHistoryId, bookingHistoryRequestDtoV3);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}

