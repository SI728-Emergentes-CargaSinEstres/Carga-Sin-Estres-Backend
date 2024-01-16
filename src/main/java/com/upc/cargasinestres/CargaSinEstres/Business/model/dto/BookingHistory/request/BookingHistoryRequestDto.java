package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

/**
 * The BookingHistoryRequestDto class represents the data transfer object for creating a booking history record.
 * It contains fields related to the details of a booking history.
 * This class is used for receiving client requests to create booking history entries.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingHistoryRequestDto {
    //private Company company;
    //private Client client;
    //private LocalDate bookingDate;
    private String pickupAddress;
    private String destinationAddress;
    private Date movingDate;
    private String movingTime;
    private String Services;

}
