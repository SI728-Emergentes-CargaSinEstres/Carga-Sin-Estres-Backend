package com.upc.cargasinestres.CargaSinEstres.Business.service.Impl;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.response.BookingHistoryResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Chat.response.ChatResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.IBookingHistoryService;
import com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations.BookingHistoryValidation;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IChatRepository;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request.BookingHistoryRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request.BookingHistoryRequestDtoV2;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.request.BookingHistoryRequestDtoV3;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.BookingHistory.response.BookingHistoryResponseDtoV2;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.BookingHistory;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IBookingHistoryRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IClientRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of the IBookingHistoryService interface.
 * Handles the business logic for booking history operations.
 * @author Grupo1
 * @version 1.0
 * */
@Service
@Qualifier("bookingHistoryServiceImpl")
public class BookingHistoryServiceImpl implements IBookingHistoryService {

    private final IBookingHistoryRepository bookingHistoryRepository;
    private final ModelMapper modelMapper;
    private final IClientRepository clientRepository;
    private final ICompanyRepository companyRepository;

    private final IChatRepository chatRepository;

    @Autowired
    public BookingHistoryServiceImpl(IBookingHistoryRepository bookingHistoryRepository, ModelMapper modelMapper,
                                     IClientRepository clientRepository, ICompanyRepository companyRepository, IChatRepository chatRepository){
        this.bookingHistoryRepository = bookingHistoryRepository;
        this.modelMapper = modelMapper;
        this.clientRepository = clientRepository;
        this.companyRepository = companyRepository;
        this.chatRepository = chatRepository;
    }

    /**
     * Creates a new booking history record.
     *
     * @param clientId               The ID of the client associated with the booking.
     * @param companyId              The ID of the company associated with the booking.
     * @param bookingHistoryRequestDto The data for creating the booking history.
     * @return The created booking history response.
     * @throws ResourceNotFoundException If the client or company is not found.
     */
    //Method : POST
    @Override
    public BookingHistoryResponseDto createBookingHistory(Long clientId, Long companyId, BookingHistoryRequestDto bookingHistoryRequestDto) {
        // Buscar la cuenta
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con ID: " + clientId));

        var company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la empresa con ID: " + companyId));

        // Validación
        BookingHistoryValidation.ValidateBookingHistory(bookingHistoryRequestDto);

        // Mapeo
        var newBookingHistory = modelMapper.map(bookingHistoryRequestDto, BookingHistory.class);
        newBookingHistory.setClient(client);
        newBookingHistory.setCompany(company);
        newBookingHistory.setBookingDate(LocalDate.now());
        newBookingHistory.setStatus("En curso");

        /* Convertir movingTime de Date a Time
        String movingTimeDate = bookingHistoryRequestDto.getMovingTime();
        Time movingTime = new Time(movingTimeDate.getTime());
        newBookingHistory.setMovingTime(movingTime);*/

        var createdBookingHistory = bookingHistoryRepository.save(newBookingHistory);
        return modelMapper.map(createdBookingHistory, BookingHistoryResponseDto.class);
    }

    /**
     * Retrieves a list of booking history records associated with a client.
     *
     * @param clientId The ID of the client.
     * @return A list of booking history response DTOs.
     */
    @Override
    public List<BookingHistoryResponseDtoV2> getBookingHistoryByClientId(Long clientId) {
        var existingBookingHistory = bookingHistoryRepository.findByClientId(clientId);
        if(existingBookingHistory.isEmpty())
            throw new ResourceNotFoundException("No se encuentran reservas para el cliente : " + clientId);


        var toShowBookingHistory = existingBookingHistory.stream()
                .map(BookingHistory -> modelMapper.map(BookingHistory, BookingHistoryResponseDtoV2.class))
                .toList();

        for (BookingHistoryResponseDtoV2 bookingHistory : toShowBookingHistory){
            var chats = chatRepository.findByBookingHistoryId(bookingHistory.getId());
            if (!chats.isEmpty()) {
                var toShowChats = chats.stream()
                        .map(chat -> modelMapper.map(chat, ChatResponseDto.class))
                        .toList();
                bookingHistory.setChats(toShowChats);
            } else {
                bookingHistory.setChats(null);
            }
        }

        return toShowBookingHistory;
    }

    /**
     * Retrieves a list of booking history records associated with a company.
     *
     * @param companyId The ID of the company.
     * @return A list of booking history response DTOs.
     */
    @Override
    public List<BookingHistoryResponseDtoV2> getBookingHistoryByCompanyId(Long companyId) {
        var existingBookingHistory = bookingHistoryRepository.findByCompanyId(companyId);
        if(existingBookingHistory.isEmpty())
            throw new ResourceNotFoundException("No se encuentran reservas para la empresa : " + companyId);

        var toShowBookingHistory =  existingBookingHistory.stream()
                .map(BookingHistory -> modelMapper.map(BookingHistory, BookingHistoryResponseDtoV2.class))
                .toList();

        for (BookingHistoryResponseDtoV2 bookingHistory : toShowBookingHistory){
            var chats = chatRepository.findByBookingHistoryId(bookingHistory.getId());
            if (chats == null)
                bookingHistory.setChats(null);
            var toShowChats = chats.stream().map(Chat -> modelMapper.map(Chat, ChatResponseDto.class)).toList();

            bookingHistory.setChats(toShowChats); //se setea la lista de chats para cada reserva
        }

        return toShowBookingHistory;


    }

    /**
     * Updates the payment field of a specific booking history.
     *
     * @param bookingHistoryId The ID of the booking history to be updated.
     * @param bookingHistoryRequestDto The data for updating the payment of the booking history.
     * @return The response of the updated booking history.
     * @throws ResourceNotFoundException If the booking history with the given ID is not found.
     * @throws ValidationException If the payment amount is not greater than 0.
     */
    @Override
    public BookingHistoryResponseDtoV2 updateBookingHistoryPayment(Long bookingHistoryId, BookingHistoryRequestDtoV2 bookingHistoryRequestDto) {
        // Buscar la reserva
        var bookingHistory = bookingHistoryRepository.findById(bookingHistoryId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el historial de reserva con ID: " + bookingHistoryId));

        // Validación
        if(bookingHistoryRequestDto.getPayment() <= 0){
            throw new ValidationException("El precio debe ser mayor a 0");
        }

        // Actualizar el campo "payment"
        bookingHistory.setPayment(bookingHistoryRequestDto.getPayment());

        // Guardar la reserva actualizada
        var updatedBookingHistory = bookingHistoryRepository.save(bookingHistory);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedBookingHistory, BookingHistoryResponseDtoV2.class);
    }

    /**
     * Updates the status field of a specific booking history.
     *
     * @param bookingHistoryId The ID of the booking history to be updated.
     * @param bookingHistoryRequestDto The data for updating the status of the booking history.
     * @return The response of the updated booking history.
     * @throws ResourceNotFoundException If the booking history with the given ID is not found.
     * @throws ValidationException If the new status is not "Finalizado".
     */
    @Override
    public BookingHistoryResponseDtoV2 updateBookingHistoryStatus(Long bookingHistoryId, BookingHistoryRequestDtoV3 bookingHistoryRequestDto) {
        // Buscar la reserva
        var bookingHistory = bookingHistoryRepository.findById(bookingHistoryId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el historial de reserva con ID: " + bookingHistoryId));

        // Validación
        if(bookingHistory.getStatus().equals("Finalizado")){
            throw new ValidationException("Esta reserva ya esta finalizada");
        }

        if(!(bookingHistoryRequestDto.getStatus().equals("Finalizado"))){
            throw new ValidationException("El estado a enviar debe ser Finalizado");
        }

        // Actualizar el campo "payment"
        bookingHistory.setStatus(bookingHistoryRequestDto.getStatus());

        // Guardar la reserva actualizada
        var updatedBookingHistory = bookingHistoryRepository.save(bookingHistory);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedBookingHistory, BookingHistoryResponseDtoV2.class);
    }


}
