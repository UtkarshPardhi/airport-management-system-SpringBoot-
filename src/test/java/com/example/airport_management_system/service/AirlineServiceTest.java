package com.example.airport_management_system.service;

import com.example.airport_management_system.dto.AirlineDto;
import com.example.airport_management_system.entity.Airline;
import com.example.airport_management_system.exception.AirlineException;
import com.example.airport_management_system.repository.AirlineRepository;
import com.example.airport_management_system.service.impl.AirlineServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirlineServiceTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineServiceImpl airlineService;

    @Test
    void createAirline_ShouldReturnSavedAirline() {

        AirlineDto airlineDto = new AirlineDto(
                null,
                "IndiGo",
                "India",
                300
        );

        Airline savedAirline = new Airline(
                1L,
                "IndiGo",
                "India",
                300,
                null
        );

        when(airlineRepository.save(any(Airline.class)))
                .thenReturn(savedAirline);

        AirlineDto result = airlineService.createAirline(airlineDto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("IndiGo", result.airlineName());
        assertEquals("India", result.country());
        assertEquals(300, result.fleetSize());

        verify(airlineRepository, times(1))
                .save(any(Airline.class));
    }

    @Test
    void getAirlineById_ShouldReturnAirline() {

        Airline airline = new Airline(
                1L,
                "IndiGo",
                "India",
                300,
                null
        );

        when(airlineRepository.findById(1L))
                .thenReturn(Optional.of(airline));

        AirlineDto result = airlineService.getAirlineById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("IndiGo", result.airlineName());
        assertEquals("India", result.country());
        assertEquals(300, result.fleetSize());

        verify(airlineRepository, times(1))
                .findById(1L);
    }

    @Test
    void getAirlineById_ShouldThrowException_WhenAirlineNotFound() {

        when(airlineRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                AirlineException.class,
                () -> airlineService.getAirlineById(1L)
        );

        verify(airlineRepository, times(1))
                .findById(1L);
    }

    @Test
    void getAllAirlines_ShouldReturnListOfAirlines() {

        Airline airline1 = new Airline(
                1L,
                "IndiGo",
                "India",
                300,
                null
        );

        Airline airline2 = new Airline(
                2L,
                "Air India",
                "India",
                250,
                null
        );

        when(airlineRepository.findAll())
                .thenReturn(Arrays.asList(airline1, airline2));

        List<AirlineDto> result = airlineService.getAllAirlines();

        assertEquals(2, result.size());

        verify(airlineRepository, times(1))
                .findAll();
    }

    @Test
    void getAllAirlines_ShouldReturnEmptyList() {

        when(airlineRepository.findAll())
                .thenReturn(List.of());

        List<AirlineDto> result = airlineService.getAllAirlines();

        assertTrue(result.isEmpty());

        verify(airlineRepository, times(1))
                .findAll();
    }

    @Test
    void deleteAirLine_ShouldDeleteAirline() {

        Airline airline = new Airline(
                1L,
                "IndiGo",
                "India",
                300,
                null
        );

        when(airlineRepository.findById(1L))
                .thenReturn(Optional.of(airline));

        airlineService.deleteAirLine(1L);

        verify(airlineRepository, times(1))
                .findById(1L);

        verify(airlineRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void deleteAirLine_ShouldThrowException_WhenAirlineNotFound() {

        when(airlineRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                AirlineException.class,
                () -> airlineService.deleteAirLine(1L)
        );

        verify(airlineRepository, times(1))
                .findById(1L);

        verify(airlineRepository, never())
                .deleteById(anyLong());
    }
}