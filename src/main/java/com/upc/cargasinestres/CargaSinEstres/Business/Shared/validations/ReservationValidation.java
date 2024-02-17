package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;


import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDto;

/**
 * The BookingHistoryValidation class provides methods for validating BookingHistoryRequestDto objects.
 * It checks for the presence and validity of essential fields in a booking history request.
 */
public class ReservationValidation {

    /**
     * Validates the provided BookingHistoryRequestDto object.
     *
     * @param reservationRequestDto The BookingHistoryRequestDto object to be validated.
     * @throws ValidationException if any validation rule is not met.
     */
    public static void ValidateBookingHistory(ReservationRequestDto reservationRequestDto){

        if(reservationRequestDto.getOrigin_address() == null || reservationRequestDto.getOrigin_address().isEmpty()){
            throw new ValidationException("La dirección de recogida debe ser obligatoria"); //error 400
        }

        if(reservationRequestDto.getDestination_address() == null || reservationRequestDto.getDestination_address().isEmpty()){
            throw new ValidationException("La dirección de destino debe ser obligatoria"); //error 400
        }

        if(reservationRequestDto.getStart_date() == null){
            throw new ValidationException("La fecha de recogida debe ser obligatoria"); //error 400
        }

        if(reservationRequestDto.getStart_time() == null){
            throw new ValidationException("El tiempo de recogida debe ser obligatorio"); //error 400
        }

        if(reservationRequestDto.getServices() == null || reservationRequestDto.getServices().isEmpty()){
            throw new ValidationException("La reserva debe presentar almenos 1 servicio, es obligatorio"); //error 400
        }


    }

}
