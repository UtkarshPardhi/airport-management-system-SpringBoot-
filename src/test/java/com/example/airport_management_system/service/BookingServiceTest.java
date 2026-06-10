package com.example.airport_management_system.service;

import com.example.airport_management_system.entity.Booking;
import com.example.airport_management_system.entity.Flight;
import com.example.airport_management_system.repository.BookingRepository;
import com.example.airport_management_system.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private FlightService flightService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void createBooking_ShouldReturnSavedBooking() {

        Flight flight = new Flight(
                1L,
                "AI101",
                "Delhi",
                "Mumbai",
                200,
                195,
                null
        );

        Booking savedBooking = new Booking(
                1L,
                "Utkarsh",
                5,
                flight
        );

        when(flightService.bookSeats(1L, 5))
                .thenReturn(flight);

        when(bookingRepository.save(any(Booking.class)))
                .thenReturn(savedBooking);

        Booking result =
                bookingService.createBooking(1L, "Utkarsh", 5);

        assertNotNull(result);
        assertEquals("Utkarsh", result.getPassengerName());
        assertEquals(5, result.getSeatsBooked());

        verify(flightService).bookSeats(1L, 5);
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    void cancelBooking_ShouldRestoreSeatsAndDeleteBooking() {

        Flight flight = new Flight(
                1L,
                "AI101",
                "Delhi",
                "Mumbai",
                200,
                190,
                null
        );

        Booking booking = new Booking(
                1L,
                "Utkarsh",
                10,
                flight
        );

        when(bookingRepository.findById(1L))
                .thenReturn(Optional.of(booking));

        Booking result =
                bookingService.cancelBooking(1L);

        assertEquals(200,
                flight.getAvailableSeats());

        verify(bookingRepository)
                .delete(booking);

        assertEquals(booking, result);
    }

    @Test
    void cancelBooking_ShouldThrowException_WhenBookingNotFound() {

        when(bookingRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> bookingService.cancelBooking(1L)
        );
    }

    @Test
    void getBookingsByFlight_ShouldReturnBookings() {

        Flight flight = new Flight(
                1L,
                "AI101",
                "Delhi",
                "Mumbai",
                200,
                200,
                null
        );

        Booking booking1 = new Booking(
                1L,
                "Utkarsh",
                2,
                flight
        );

        Booking booking2 = new Booking(
                2L,
                "Rahul",
                3,
                flight
        );

        when(bookingRepository.findByFlightId(1L))
                .thenReturn(Arrays.asList(
                        booking1,
                        booking2
                ));

        List<Booking> result =
                bookingService.getBookingsByFlight(1L);

        assertEquals(2, result.size());

        verify(bookingRepository)
                .findByFlightId(1L);
    }

    @Test
    void getBookingsByPassenger_ShouldReturnBookings() {

        Flight flight = new Flight(
                1L,
                "AI101",
                "Delhi",
                "Mumbai",
                200,
                200,
                null
        );

        Booking booking = new Booking(
                1L,
                "Utkarsh",
                2,
                flight
        );

        when(bookingRepository
                .findByPassengerNameIgnoreCase("Utkarsh"))
                .thenReturn(List.of(booking));

        List<Booking> result =
                bookingService.getBookingsByPassenger("Utkarsh");

        assertEquals(1, result.size());
        assertEquals(
                "Utkarsh",
                result.get(0).getPassengerName()
        );

        verify(bookingRepository)
                .findByPassengerNameIgnoreCase("Utkarsh");
    }
}