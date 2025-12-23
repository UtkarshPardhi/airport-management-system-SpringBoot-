package com.example.airport_management_system.service.impl;

import com.example.airport_management_system.entity.Booking;
import com.example.airport_management_system.entity.Flight;
import com.example.airport_management_system.repository.BookingRepository;
import com.example.airport_management_system.service.BookingService;
import com.example.airport_management_system.service.FlightService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private FlightService flightService;

    public BookingServiceImpl(BookingRepository bookingRepository, FlightService flightService) {
        this.bookingRepository = bookingRepository;
        this.flightService = flightService;
    }


    @Override
    @Transactional
    public Booking createBooking(Long flightId, String passengerName, int seats) {

        // Reduce seats safely
        Flight flight = flightService.bookSeats(flightId, seats);

        // Create booking
        Booking booking = new Booking();
        booking.setPassengerName(passengerName);
        booking.setSeatsBooked(seats);
        booking.setFlight(flight);

        // Save booking
        return bookingRepository.save(booking);
    }
    @Override
    @Transactional
    public Booking cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Flight flight = booking.getFlight();

        // restore seats
        flight.setAvailableSeats(
                flight.getAvailableSeats() + booking.getSeatsBooked()
        );

        // delete booking
        bookingRepository.delete(booking);

        return booking; // optional: return cancelled booking details
    }

    @Override
    public List<Booking> getBookingsByFlight(Long flightId) {
        return bookingRepository.findByFlightId(flightId);
    }

    @Override
    public List<Booking> getBookingsByPassenger(String passengerName) {
        return bookingRepository.findByPassengerNameIgnoreCase(passengerName);
    }


}
