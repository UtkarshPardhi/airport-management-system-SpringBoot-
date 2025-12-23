package com.example.airport_management_system.controller;

import com.example.airport_management_system.entity.Flight;
import com.example.airport_management_system.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // CREATE Flight under Airline REST API
    @PostMapping("/airline/{airlineId}")
    public ResponseEntity<Flight> createFlight(
            @PathVariable Long airlineId,
            @RequestBody Flight flight) {

        return new ResponseEntity<>(
                flightService.createFlight(airlineId, flight),
                HttpStatus.CREATED
        );
    }

    //  GET Flights of an Airline REST API
    @GetMapping("/airline/{airlineId}")
    public ResponseEntity<List<Flight>> getFlightsByAirline(
            @PathVariable Long airlineId) {

        return ResponseEntity.ok(
                flightService.getFlightsByAirline(airlineId)
        );
    }
    // Get flight by id REST API
    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(
            @PathVariable Long flightId){
        return ResponseEntity.ok(flightService.getFlightById(flightId));
    }

    // Get flight by flight no. REST API
    @GetMapping("/number/{flightNumber}")
    public ResponseEntity<Flight> getFlightByNumber(
            @PathVariable String flightNumber) {

        return ResponseEntity.ok(
                flightService.getByFlightNumber(flightNumber)
        );
    }

    // Seat Booking REST API
    @PostMapping("/{flightId}/book")
    public ResponseEntity<Flight> bookSeats(
            @PathVariable Long flightId,
            @RequestParam int seats) {

        return ResponseEntity.ok(
                flightService.bookSeats(flightId, seats)
        );
    }


}
