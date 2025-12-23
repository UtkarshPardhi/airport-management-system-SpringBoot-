package com.example.airport_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "seats_booked")
    private int seatsBooked;


    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;
}
