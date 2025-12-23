package com.example.airport_management_system.exception;

public class InsufficientSeatsException extends RuntimeException {
    public InsufficientSeatsException(String message){
        super(message);
    }
}
