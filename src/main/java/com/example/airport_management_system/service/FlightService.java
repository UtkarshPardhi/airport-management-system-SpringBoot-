package com.example.airport_management_system.service;

import com.example.airport_management_system.entity.Flight;

import java.util.List;

public interface FlightService {

    Flight createFlight(Long airlineId, Flight flight);

    List<Flight> getFlightsByAirline(Long airlineId);

    Flight getFlightById(Long flightId);

    Flight getByFlightNumber(String flightNumber);

    Flight bookSeats(Long flightId, int seats);

}
