package com.example.airport_management_system.service;

import com.example.airport_management_system.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(Long flightId,
                          String passengerName,
                          int seats);

    Booking cancelBooking(Long bookingId);

    List<Booking> getBookingsByFlight(Long flightId);

    List<Booking> getBookingsByPassenger(String passengerName);
}
