package com.example.airport_management_system.repository;

import com.example.airport_management_system.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByFlightId(Long flightId);
    List<Booking> findByPassengerNameIgnoreCase(String passengerName);

}
