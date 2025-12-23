package com.example.airport_management_system.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timeStamp,
                          String message,
                          String details,
                          String errorCode) {

}
