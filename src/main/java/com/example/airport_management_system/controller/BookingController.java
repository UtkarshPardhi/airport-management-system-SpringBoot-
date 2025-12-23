package com.example.airport_management_system.controller;

import com.example.airport_management_system.entity.Booking;
import com.example.airport_management_system.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Booking REST API
    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long flightId,
            @RequestParam String passengerName,
            @RequestParam int seats) {

        return ResponseEntity.ok(
                bookingService.createBooking(flightId, passengerName, seats)
        );
    }

    // Cancel booking REST API
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking cancelled and seats restored");
    }

    // Booking seats by flightId REST API
    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<Booking>> getBookingsByFlight(
            @PathVariable Long flightId) {
        return ResponseEntity.ok(
                bookingService.getBookingsByFlight(flightId)
        );
    }

    // Booking by passenger REST API
    @GetMapping("/passenger/{name}")
    public ResponseEntity<List<Booking>> getBookingsByPassenger(
            @PathVariable String name) {
        return ResponseEntity.ok(
                bookingService.getBookingsByPassenger(name)
        );
    }

}
