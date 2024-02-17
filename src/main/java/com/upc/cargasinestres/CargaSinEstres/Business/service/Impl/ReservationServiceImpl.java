package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.response.ReservationResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.response.ChatResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IReservationService;
import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.ReservationValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IChatRepository;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDtoV2;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDtoV3;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Reservation;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IReservationRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICustomerRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the IBookingHistoryService interface.
 * Handles the business logic for booking history operations.
 *
 * @author Grupo1
 * @version 1.0
 */
@Service
@Qualifier("reservationServiceImpl")
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final ModelMapper modelMapper;
    private final ICustomerRepository customerRepository;
    private final ICompanyRepository companyRepository;

    private final IChatRepository chatRepository;

    @Autowired
    public ReservationServiceImpl(IReservationRepository reservationRepository, ModelMapper modelMapper,
                                  ICustomerRepository customerRepository, ICompanyRepository companyRepository, IChatRepository chatRepository) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
        this.chatRepository = chatRepository;
    }

    /**
     * Creates a new booking history record.
     *
     * @param customerId              The ID of the client associated with the booking.
     * @param companyId             The ID of the company associated with the booking.
     * @param reservationRequestDto The data for creating the booking history.
     * @return The created booking history response.
     * @throws ResourceNotFoundException If the client or company is not found.
     */
    //Method : POST
    @Override
    public ReservationResponseDto createReservation(Long customerId, Long companyId, ReservationRequestDto reservationRequestDto) {
        // Buscar la cuenta
        var client = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con ID: " + customerId));

        var company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la empresa con ID: " + companyId));

        // Validación
        ReservationValidation.ValidateBookingHistory(reservationRequestDto);

        // Mapeo
        var newBookingHistory = modelMapper.map(reservationRequestDto, Reservation.class);
        newBookingHistory.setCustomer(client);
        newBookingHistory.setCompany(company);
        /*
        newBookingHistory.setBookingDate(LocalDate.now());
        */
        newBookingHistory.setStatus("solicited");

        /* Convertir movingTime de Date a Time
        String movingTimeDate = bookingHistoryRequestDto.getMovingTime();
        Time movingTime = new Time(movingTimeDate.getTime());
        newBookingHistory.setMovingTime(movingTime);*/

        var createdBookingHistory = reservationRepository.save(newBookingHistory);
        return modelMapper.map(createdBookingHistory, ReservationResponseDto.class);
    }

    /**
     * Retrieves a list of booking history records associated with a client.
     *
     * @param clientId The ID of the client.
     * @return A list of booking history response DTOs.
     */
    @Override
    public List<ReservationResponseDto> getReservationByCustomerId(Long clientId) {
        var existingReservation = reservationRepository.findByCustomerId(clientId);
        if (existingReservation.isEmpty())
            throw new ResourceNotFoundException("No se encuentran reservas para el cliente : " + clientId);

        var toShowReservations = existingReservation.stream()
                .map(Reservation -> modelMapper.map(Reservation, ReservationResponseDto.class))
                .toList();

        for (ReservationResponseDto reservation : toShowReservations) {
            var Chat = chatRepository.findByReservationId(reservation.getId());
            reservation.setChat_id(Chat.getId());

        }

        return toShowReservations;
    }

    /**
     * Retrieves a list of booking history records associated with a company.
     *
     * @param companyId The ID of the company.
     * @return A list of booking history response DTOs.
     */
    @Override
    public List<ReservationResponseDto> getReservationByCompanyId(Long companyId) {
        var existingReservation = reservationRepository.findByCompanyId(companyId);
        if (existingReservation.isEmpty())
            throw new ResourceNotFoundException("No se encuentran reservas para el cliente : " + companyId);

        var toShowReservations = existingReservation.stream()
                .map(Reservation -> modelMapper.map(Reservation, ReservationResponseDto.class))
                .toList();

        for (ReservationResponseDto reservation : toShowReservations) {
            var Chat = chatRepository.findByReservationId(reservation.getId());
            reservation.setChat_id(Chat.getId());
        }
        return toShowReservations;

    }

    /**
     * Updates the payment field of a specific booking history.
     *
     * @param reservationId         The ID of the booking history to be updated.
     * @param price The data for updating the payment of the booking history.
     * @return The response of the updated booking history.
     * @throws ResourceNotFoundException If the booking history with the given ID is not found.
     * @throws ValidationException       If the payment amount is not greater than 0.
     */
    @Override
    public ReservationResponseDto updateReservationPrice(Long reservationId, float price) {
        // Buscar la reserva
        var bookingHistory = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el historial de reserva con ID: " + reservationId));

        // Validación
        if (price <= 0) {
            throw new ValidationException("El precio debe ser mayor a 0");
        }

        // Actualizar el campo "payment"
        bookingHistory.setPrice(price);

        // Guardar la reserva actualizada
        var updatedBookingHistory = reservationRepository.save(bookingHistory);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedBookingHistory, ReservationResponseDto.class);
    }

    /**
     * Updates the status field of a specific booking history.
     *
     * @param bookingHistoryId         The ID of the booking history to be updated.
     * @param bookingHistoryRequestDto The data for updating the status of the booking history.
     * @return The response of the updated booking history.
     * @throws ResourceNotFoundException If the booking history with the given ID is not found.
     * @throws ValidationException       If the new status is not "Finalizado".
     */
    @Override
    public ReservationResponseDto updateReservationStatus(Long bookingHistoryId, ReservationRequestDtoV3 bookingHistoryRequestDto) {
        // Buscar la reserva
        var bookingHistory = reservationRepository.findById(bookingHistoryId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el historial de reserva con ID: " + bookingHistoryId));

        // Validación
        if (bookingHistory.getStatus().equals("finished") || bookingHistory.getStatus().equals("cancelled")) {
            throw new ValidationException("Esta reserva ya esta finalizada");
        }

        if (!(bookingHistoryRequestDto.getStatus().equals("finished")) && !(bookingHistoryRequestDto.getStatus().equals("cancelled"))) {
            throw new ValidationException("El estado a enviar debe ser Finalizado");
        }

        // Actualizar el campo "payment"
        bookingHistory.setStatus(bookingHistoryRequestDto.getStatus());

        // Guardar la reserva actualizada
        var updatedBookingHistory = reservationRepository.save(bookingHistory);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedBookingHistory, ReservationResponseDto.class);
    }


}
