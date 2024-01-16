package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;


import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request.BookingHistoryRequestDto;

/**
 * The BookingHistoryValidation class provides methods for validating BookingHistoryRequestDto objects.
 * It checks for the presence and validity of essential fields in a booking history request.
 */
public class BookingHistoryValidation {

    /**
     * Validates the provided BookingHistoryRequestDto object.
     *
     * @param bookingHistoryRequestDto The BookingHistoryRequestDto object to be validated.
     * @throws ValidationException if any validation rule is not met.
     */
    public static void ValidateBookingHistory(BookingHistoryRequestDto bookingHistoryRequestDto){

        if(bookingHistoryRequestDto.getPickupAddress() == null || bookingHistoryRequestDto.getPickupAddress().isEmpty()){
            throw new ValidationException("La dirección de recogida debe ser obligatoria"); //error 400
        }

        if(bookingHistoryRequestDto.getDestinationAddress() == null || bookingHistoryRequestDto.getDestinationAddress().isEmpty()){
            throw new ValidationException("La dirección de destino debe ser obligatoria"); //error 400
        }

        if(bookingHistoryRequestDto.getMovingDate() == null){
            throw new ValidationException("La fecha de recogida debe ser obligatoria"); //error 400
        }

        if(bookingHistoryRequestDto.getMovingTime() == null){
            throw new ValidationException("El tiempo de recogida debe ser obligatorio"); //error 400
        }

        if(bookingHistoryRequestDto.getServices() == null || bookingHistoryRequestDto.getServices().isEmpty()){
            throw new ValidationException("La reserva debe presentar almenos 1 servicio, es obligatorio"); //error 400
        }


    }

}
