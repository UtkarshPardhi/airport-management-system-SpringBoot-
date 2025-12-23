package com.example.airport_management_system.service.impl;

import com.example.airport_management_system.dto.AirlineDto;
import com.example.airport_management_system.entity.Airline;
import com.example.airport_management_system.exception.AirlineException;
import com.example.airport_management_system.mapper.AirlineMapper;
import com.example.airport_management_system.repository.AirlineRepository;
import com.example.airport_management_system.service.AirlineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirlineServiceImpl implements AirlineService {

    private AirlineRepository airlineRepository;

    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public AirlineDto createAirline(AirlineDto airlineDto) {
        Airline airline = AirlineMapper.mapToAirline(airlineDto);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.mapToAirlineDto(savedAirline);
    }

    @Override
    public List<AirlineDto> getAllAirlines() {
        List<Airline> airlines = airlineRepository.findAll();
        return airlines.stream().map((airline) -> AirlineMapper.mapToAirlineDto(airline))
                .collect(Collectors.toList());
    }

    @Override
    public AirlineDto getAirlineById(Long id) {
        Airline airline = airlineRepository
                .findById(id)
                .orElseThrow(() -> new AirlineException("Airline does not exists"));
        return AirlineMapper.mapToAirlineDto(airline);
    }

    @Override
    public void deleteAirLine(Long id) {

        Airline airline = airlineRepository
                .findById(id)
                .orElseThrow(() -> new AirlineException("Airline does not exists"));

        airlineRepository.deleteById(id);

    }

}