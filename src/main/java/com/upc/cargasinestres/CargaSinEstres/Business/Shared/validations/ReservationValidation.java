package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;


import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;

/**
 * The reservationValidation class provides methods for validating reservationRequestDto objects.
 * It checks for the presence and validity of essential fields in a booking history request.
 */
public class ReservationValidation {

    /**
     * Validates the provided ReservationRequestDto object.
     *
     * @param reservationRequestDto The ReservationRequestDto object to be validated.
     * @throws ValidationException if any validation rule is not met.
     */
    public static void ValidateReservation(ReservationRequestDto reservationRequestDto){

        if(reservationRequestDto.getOriginAddress() == null || reservationRequestDto.getOriginAddress().isEmpty()){
            throw new ValidationException("La dirección de recogida debe ser obligatoria"); //error 400
        }

        if(reservationRequestDto.getDestinationAddress() == null || reservationRequestDto.getDestinationAddress().isEmpty()){
            throw new ValidationException("La dirección de destino debe ser obligatoria"); //error 400
        }

        if(reservationRequestDto.getStartDate() == null){
            throw new ValidationException("La fecha de recogida debe ser obligatoria"); //error 400
        }

        if(reservationRequestDto.getStartTime() == null){
            throw new ValidationException("El tiempo de recogida debe ser obligatorio"); //error 400
        }

        if(reservationRequestDto.getServicios() == null || reservationRequestDto.getServicios().isEmpty()){
            throw new ValidationException("La reserva debe presentar almenos 1 servicio, es obligatorio"); //error 400
        }

        Date ahora = new Date();
        if(reservationRequestDto.getStartDate().before(ahora)){
            throw new ValidationException("La fecha de inicio de la reserva no puede ser en el pasado."); //error 400
        }


    }

    /*public static void ValidateReservationStatus(ReservationRequestDto reservationRequestDto) {
        if (!(reservationRequestDto.getStatus().equals("finished")) && !(reservationRequestDto.getStatus().equals("cancelled"))) {
            throw new ValidationException("El estado a enviar debe ser Finalizado");
        }
    }*/

/*
    // Validación
        if (reservation.getStatus().equals("finished") || reservation.getStatus().equals("cancelled")) {
        throw new ValidationException("Esta reserva ya esta finalizada");
    }
*/
}
