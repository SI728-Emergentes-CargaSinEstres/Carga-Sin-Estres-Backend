package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.response;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Servicio.request.ServicioRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Customer;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Servicio;
import lombok.AllArgsConstructor;
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
public class ReservationResponseDto {
    private Long id;
    private float price;
    private String originAddress;
    private String destinationAddress;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private String status;
    private List<ServicioRequestDto> servicios;
    private Long chatId;
    private Company company;
    private Customer customer;
}
