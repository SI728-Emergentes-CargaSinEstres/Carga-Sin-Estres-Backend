package com.upc.cargasinestres.CargaSinEstres.Business.Reservation;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.request.ReservationRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Reservation.response.ReservationResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Customer;
import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Reservation;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICompanyRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.ICustomerRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.repository.IReservationRepository;
import com.upc.cargasinestres.CargaSinEstres.Business.service.Impl.ReservationServiceImpl;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @Mock
    private IReservationRepository reservationRepository;
    @Mock
    private ICustomerRepository customerRepository;
    @Mock
    private ICompanyRepository companyRepository;
    @InjectMocks
    private ReservationServiceImpl reservationService;
    private ReservationRequestDto reservationRequestDto;
    private Reservation reservation;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationServiceImpl(reservationRepository, new ModelMapper(), customerRepository, companyRepository);
        reservationRequestDto = ReservationRequestDto.builder().startTime("12:00").originAddress("Av. Origen 123")
                .destinationAddress("Av, Origen 123").startDate(LocalDate.now()).services("transporte").build();
        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setStatus("solicited");
        reservation.setPrice(100.0f);
        reservation.setStartDate(LocalDate.now());
        reservation.setStartTime(LocalTime.MIDNIGHT);
    }

    @DisplayName("Test to create new reservation")
    @Test
    void testCreateReservation_HappyPath(){

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer()));
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(new Company()));
        // Test the method
        ReservationResponseDto responseDto = reservationService
                .createReservation(1L, 1L, reservationRequestDto);
        assertEquals("solicited", responseDto.getStatus());
    }

    @DisplayName("Test to create a reservation but client not found")
    @Test
    void testCreateReservation_UnhappyPath_ClientNotFound(){
        // Mocking repository calls
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Calling the method and expecting an exception
        assertThrows(ResourceNotFoundException.class, () -> {
            reservationService
                    .createReservation(1L, 1L, reservationRequestDto);
        });
    }

    private Customer mockCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        // Set other necessary fields in customer
        return customer;
    }

    @DisplayName("Test to create reservation but company not found")
    @Test
    void createReservation_UnhappyPath_CompanyNotFound() {
        // Mocking repository calls
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(mockCustomer()));
        when(companyRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Creating request DTO
        ReservationRequestDto requestDto = new ReservationRequestDto();
        requestDto.setStartTime("09:00");
        // You should set other necessary fields in requestDto

        // Calling the method and expecting an exception
        assertThrows(ResourceNotFoundException.class, () -> {
            reservationService.createReservation(1L, 1L, requestDto);
        });
    }

    @DisplayName("Test to get reservation by Customer Id")
    @Test
    public void testGetReservationByCustomerId_HappyPath() {
        when(reservationRepository.findByCustomerId(anyLong())).thenReturn(Collections.singletonList(reservation));

        List<ReservationResponseDto> result = reservationService.getReservationByCustomerId(123L);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(reservation.getId(), result.get(0).getId());
    }

    @DisplayName("Test to get reservation by costumer id (unhappy path)")
    @Test
    public void testGetReservationByCustomerId_UnhappyPath() {
        when(reservationRepository.findByCustomerId(anyLong())).thenReturn(Collections.emptyList());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            reservationService.getReservationByCustomerId(123L);
        });
    }

    @DisplayName("Test to update reservation price start date and start time")
    @Test
    public void testUpdateReservationPriceStartDateStartTime_HappyPath() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        ReservationResponseDto result = reservationService.updateReservationPriceStartDateStartTime(1L, 200.0f, LocalDate.now(), "12:00:00");

        Assertions.assertEquals(200.0f, result.getPrice());
    }

    @DisplayName("Test to update to reservation price, start date, start time")
    @Test
    public void testUpdateReservationPriceStartDateStartTime_UnhappyPath() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            reservationService.updateReservationPriceStartDateStartTime(1L, 200.0f, LocalDate.now(), "12:00:00");
        });
    }

    @DisplayName("Test to update reservation status")
    @Test
    public void testUpdateReservationStatus_HappyPath() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        ReservationResponseDto result = reservationService.updateReservationStatus(1L, "finalized");

        Assertions.assertEquals("finalized", result.getStatus());
    }

    @DisplayName("Test to update reservation status (unhappy path)")
    @Test
    public void testUpdateReservationStatus_UnhappyPath() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            reservationService.updateReservationStatus(1L, "finalized");
        });
    }

    @DisplayName("Test update reservation chat id")
    @Test
    public void testUpdateReservationChatId_Success() {
        // Arrange
        Long reservationId = 1L;
        Long chatId = 100L;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        ReservationResponseDto result = reservationService.updateReservationChatId(reservationId, chatId);

        // Assert
        assertNotNull(result);
        assertEquals(chatId, result.getChatId());
    }

    @DisplayName("Test to update reservation chat id (unhappy path)")
    @Test
    public void testUpdateReservationChatId_NotFound() {
        // Arrange
        Long reservationId = 1L;
        Long chatId = 100L;
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> reservationService.updateReservationChatId(reservationId, chatId));
    }

    @DisplayName("Test to get reservation by id")
    @Test
    public void testGetById_Success() {
        // Arrange
        Long resId = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(resId)).thenReturn(Optional.of(reservation));

        // Act
        Reservation result = reservationService.getById(resId);

        // Assert
        assertNotNull(result);
        assertEquals(reservation, result);
    }

    @DisplayName("Test to get reservation by id but not found")
    @Test
    public void testGetById_NotFound() {
        // Arrange
        Long resId = 1L;
        when(reservationRepository.findById(resId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> reservationService.getById(resId));
    }

    @DisplayName("Test to get reservation by company id")
    @Test
    public void testGetReservationByCompanyId() {
        // Mocking data
        Long companyId = 1L;
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        when(reservationRepository.findByCompanyId(companyId)).thenReturn(reservations);

        // Call service method
        List<ReservationResponseDto> result = reservationService
                .getReservationByCompanyId(companyId);

        // Assert
        assertEquals(reservations.size(), result.size());
    }

    @DisplayName("Test to get reservation by company id, but no reservation found")
    @Test
    public void testGetReservationByCompanyId_NoReservationFound() {
        // Mocking data
        Long companyId = 1L;
        when(reservationRepository.findByCompanyId(companyId)).thenReturn(List.of());

        // Call service method and assert exception
        assertThrows(ResourceNotFoundException.class, () -> {
            reservationService.getReservationByCompanyId(companyId);
        });
    }
}
