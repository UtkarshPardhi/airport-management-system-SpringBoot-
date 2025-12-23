package com.example.airport_management_system.dto;

public record AirlineDto(Long id,
                         String airlineName,
                         String country,
                         Integer fleetSize) {
}
