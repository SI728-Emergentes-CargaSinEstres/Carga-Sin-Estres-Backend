package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.response;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.response.ChatResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Customer;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * This class represents the response of the booking history
 * @author Grupo1
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDtoV2 {
    private Long id;
    private Company company;
    private Customer customer;
    private LocalDate bookingDate;
    private String pickupAddress;
    private String destinationAddress;
    private Date start_date;
    private String start_time;
    private Date end_date;
    private String status;
    private String Services;
    private float price;
    private List<ChatResponseDto> chats; // la conversion se hace dentro del servicio

}
