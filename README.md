# Airport Management System - Spring Boot

A backend Airport Management System built using Spring Boot, Spring Data JPA, and PostgreSQL.
This project manages Airlines, Flights, Seat Booking, and Passengers using RESTful APIs.

--------------------------------------------------

FEATURES

Airline Management
- Create new airlines
- Get all airlines
- Get airline by ID
- Delete airline

Flight Management
- Create flights under an airline
- Get flights by airline
- Get flight by ID
- Automatically set available seats

Seat Booking
- Book seats for a flight
- Prevent overbooking
- Handle insufficient seats using custom exception

Booking Management
- Create booking
- Get booking by passenger name
- Get booking by booking ID

Exception Handling
- Global exception handling using ControllerAdvice
- Custom exceptions for Airline, Flight, and Seat availability

--------------------------------------------------

PROJECT STRUCTURE

src/main/java/com/example/airport_management_system
|-- controller
|-- service
|   |-- impl
|-- repository
|-- entity
|-- dto
|-- mapper
|-- exception
|-- AirportManagementSystemApplication.java

--------------------------------------------------

TECH STACK

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- IntelliJ IDEA
- Postman

--------------------------------------------------

API ENDPOINTS

Airlines
POST    /api/airlines
GET     /api/airlines
GET     /api/airlines/{id}
DELETE  /api/airlines/{id}

Flights
POST    /api/flights/{airlineId}/flights
GET     /api/flights/{airlineId}/flights
GET     /api/flights/{flightId}

Bookings
POST    /api/bookings
GET     /api/bookings/{id}
GET     /api/bookings/passenger/{name}

--------------------------------------------------

DATABASE CONFIGURATION

spring.datasource.url=jdbc:postgresql://localhost:5432/airport_management
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

--------------------------------------------------

HOW TO RUN

git clone https://github.com/UtkarshPardhi/airport-management-system-SpringBoot-.git
cd airport-management-system
mvn spring-boot:run

Application runs on:
http://localhost:8080

--------------------------------------------------

AUTHOR

Utkarsh Pardhi
Java Backend Developer
GitHub: https://github.com/UtkarshPardhi
