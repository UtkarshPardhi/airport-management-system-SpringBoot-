package com.example.airport_management_system.repository;

import com.example.airport_management_system.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByAirlineId(Long airlineId);
    Optional<Flight> findByFlightNumber(String flightNumber);

}
