package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.response;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.response.ChatResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Client.response.ClientResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Company.response.CompanyResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Client;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
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
public class BookingHistoryResponseDtoV2 {
    private Long id;
    private Company company;
    private Client client;
    private LocalDate bookingDate;
    private String pickupAddress;
    private String destinationAddress;
    private Date movingDate;
    private String movingTime;
    private String status;
    private String Services;
    private int payment;
    private List<ChatResponseDto> chats; // la conversion se hace dentro del servicio

        /*
    private Long id;  -------------------------------
    private Company company; -------------------------------
    private Client client; -------------------------------
    private LocalDate bookingDate;  -------------------------------
    private String pickupAddress;   -------------------------------
    private String destinationAddress; -------------------------------
    private Date movingDate; -------------------------------
    private Time movingTime; -------------------------------
    private String status; -------------------------------
    private String Services; -------------------------------
    private Payment payment;
    private HiredCompany hiredCompany;
    private List<Chat> chats; ------------------------------
     */
}
