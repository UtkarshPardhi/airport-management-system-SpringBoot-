package com.example.airport_management_system.controller;

import com.example.airport_management_system.dto.AirlineDto;
import com.example.airport_management_system.service.AirlineService;
import com.example.airport_management_system.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private AirlineService airlineService;
    private FlightService flightService;

    public AirlineController(AirlineService airlineService, FlightService flightService) {
        this.airlineService = airlineService;
        this.flightService = flightService;
    }

    // CREATE Airline REST API
    @PostMapping
    public ResponseEntity<AirlineDto> createAirline(@RequestBody AirlineDto airlineDto){
        return new ResponseEntity<>(airlineService.createAirline(airlineDto), HttpStatus.CREATED);
    }

    // GET all Airlines REST API
    @GetMapping
    public ResponseEntity<List<AirlineDto>> getAllAirlines() {
        List<AirlineDto> airlines = airlineService.getAllAirlines();
        return ResponseEntity.ok(airlines);
    }

    // GET Airline by ID REST API
    @GetMapping("/{id}")
    public ResponseEntity<AirlineDto> getAirlineById(@PathVariable Long id){
        AirlineDto airlineDto = airlineService.getAirlineById(id);
        return ResponseEntity.ok(airlineDto);
    }

    // DELETE Airline REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirline(@PathVariable Long id){
        airlineService.deleteAirLine(id);
        return ResponseEntity.ok("Airline is deleted successfully");
    }


}
