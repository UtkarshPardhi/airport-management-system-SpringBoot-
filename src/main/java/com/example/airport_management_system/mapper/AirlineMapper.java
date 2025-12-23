package com.example.airport_management_system.mapper;

import com.example.airport_management_system.dto.AirlineDto;
import com.example.airport_management_system.entity.Airline;

public class AirlineMapper {
    public static Airline mapToAirline(AirlineDto airlineDto){
        Airline airline = new Airline();
                //airlineDto.id(),
                //airlineDto.airlineName(),
                //airlineDto.country(),
                //airlineDto.fleetSize()
                airline.setId(airlineDto.id());
                airline.setAirlineName(airlineDto.airlineName());
                airline.setCountry(airlineDto.country());
                airline.setFleetSize(airlineDto.fleetSize());
                return airline;
    }

    public static AirlineDto mapToAirlineDto(Airline airline){
        AirlineDto airlineDto = new AirlineDto(
                airline.getId(),
                airline.getAirlineName(),
                airline.getCountry(),
                airline.getFleetSize()
        );
        return airlineDto;
    }
}
