package com.example.airport_management_system.service;

import com.example.airport_management_system.dto.AirlineDto;
import com.example.airport_management_system.entity.Airline;

import java.util.List;

public interface AirlineService {

    AirlineDto createAirline(AirlineDto airlineDto);

    List<AirlineDto> getAllAirlines();

    AirlineDto getAirlineById(Long id);

    void deleteAirLine(Long id);
}
