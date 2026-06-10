package com.example.airport_management_system.service;

import com.example.airport_management_system.entity.Airline;
import com.example.airport_management_system.entity.Flight;
import com.example.airport_management_system.exception.InsufficientSeatsException;
import com.example.airport_management_system.repository.AirlineRepository;
import com.example.airport_management_system.repository.FlightRepository;
import com.example.airport_management_system.service.impl.FlightServiceImpl;
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
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @Test
    void createFlight_ShouldReturnSavedFlight() {

        Airline airline = new Airline(
                1L,
                "IndiGo",
                "India",
                300,
                null
        );

        Flight flight = new Flight(
                null,
                "6E101",
                "Nagpur",
                "Mumbai",
                180,
                null,
                null
        );

        Flight savedFlight = new Flight(
                1L,
                "6E101",
                "Nagpur",
                "Mumbai",
                180,
                180,
                airline
        );

        when(airlineRepository.findById(1L))
                .thenReturn(Optional.of(airline));

        when(flightRepository.save(any(Flight.class)))
                .thenReturn(savedFlight);

        Flight result = flightService.createFlight(1L, flight);

        assertNotNull(result);
        assertEquals("6E101", result.getFlightNumber());
        assertEquals(180, result.getAvailableSeats());

        verify(airlineRepository).findById(1L);
        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    void getFlightById_ShouldReturnFlight() {

        Flight flight = new Flight(
                1L,
                "AI101",
                "Delhi",
                "Mumbai",
                200,
                200,
                null
        );

        when(flightRepository.findById(1L))
                .thenReturn(Optional.of(flight));

        Flight result = flightService.getFlightById(1L);

        assertNotNull(result);
        assertEquals("AI101", result.getFlightNumber());

        verify(flightRepository).findById(1L);
    }

    @Test
    void getFlightById_ShouldThrowException() {

        when(flightRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> flightService.getFlightById(1L)
        );
    }

    @Test
    void getByFlightNumber_ShouldReturnFlight() {

        Flight flight = new Flight(
                1L,
                "AI101",
                "Delhi",
                "Mumbai",
                200,
                200,
                null
        );

        when(flightRepository.findByFlightNumber("AI101"))
                .thenReturn(Optional.of(flight));

        Flight result = flightService.getByFlightNumber("AI101");

        assertEquals("AI101", result.getFlightNumber());

        verify(flightRepository).findByFlightNumber("AI101");
    }

    @Test
    void getFlightsByAirline_ShouldReturnFlights() {

        Flight flight1 = new Flight(
                1L,
                "AI101",
                "Delhi",
                "Mumbai",
                200,
                200,
                null
        );

        Flight flight2 = new Flight(
                2L,
                "AI102",
                "Delhi",
                "Pune",
                150,
                150,
                null
        );

        when(flightRepository.findByAirlineId(1L))
                .thenReturn(Arrays.asList(flight1, flight2));

        List<Flight> flights = flightService.getFlightsByAirline(1L);

        assertEquals(2, flights.size());

        verify(flightRepository).findByAirlineId(1L);
    }

    @Test
    void bookSeats_ShouldReduceAvailableSeats() {

        Flight flight = new Flight(
                1L,
                "AI101",
                "Delhi",
                "Mumbai",
                200,
                100,
                null
        );

        when(flightRepository.findById(1L))
                .thenReturn(Optional.of(flight));

        when(flightRepository.save(any(Flight.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Flight result = flightService.bookSeats(1L, 5);

        assertEquals(95, result.getAvailableSeats());

        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    void bookSeats_ShouldThrowInsufficientSeatsException() {

        Flight flight = new Flight(
                1L,
                "AI101",
                "Delhi",
                "Mumbai",
                200,
                2,
                null
        );

        when(flightRepository.findById(1L))
                .thenReturn(Optional.of(flight));

        assertThrows(
                InsufficientSeatsException.class,
                () -> flightService.bookSeats(1L, 5)
        );
    }

    @Test
    void bookSeats_ShouldThrowExceptionForInvalidSeatCount() {

        assertThrows(
                RuntimeException.class,
                () -> flightService.bookSeats(1L, 0)
        );
    }
}