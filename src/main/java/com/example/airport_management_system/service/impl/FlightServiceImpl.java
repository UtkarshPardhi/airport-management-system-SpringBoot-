package com.example.airport_management_system.service.impl;

import com.example.airport_management_system.entity.Airline;
import com.example.airport_management_system.entity.Flight;
import com.example.airport_management_system.exception.InsufficientSeatsException;
import com.example.airport_management_system.repository.AirlineRepository;
import com.example.airport_management_system.repository.FlightRepository;
import com.example.airport_management_system.service.FlightService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    private AirlineRepository airlineRepository;

    public FlightServiceImpl(FlightRepository flightRepository, AirlineRepository airlineRepository) {
        this.flightRepository = flightRepository;
        this.airlineRepository = airlineRepository;
    }


    @Override
    public Flight createFlight(Long airlineId, Flight flight) {
        Airline airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> new RuntimeException("Airline not found"));

        flight.setAirline(airline);
        flight.setAvailableSeats(flight.getTotalSeats());

        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getFlightsByAirline(Long airlineId) {
        return flightRepository.findByAirlineId(airlineId);
    }

    @Override
    public Flight getFlightById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(()-> new RuntimeException("Flight not found"));
    }

    @Override
    public Flight getByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found with number: " + flightNumber));
    }

    @Override
    @Transactional
    public Flight bookSeats(Long flightId, int seats) {

        if (seats <= 0) {
            throw new RuntimeException("Seat count must be greater than zero");
        }

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        if (flight.getAvailableSeats() < seats) {
            throw new InsufficientSeatsException("Not enough seats available");
        }

        flight.setAvailableSeats(
                flight.getAvailableSeats() - seats
        );

        return flightRepository.save(flight);
    }
}
