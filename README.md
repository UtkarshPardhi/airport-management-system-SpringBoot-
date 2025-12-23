📌 Airport Management System – Spring Boot

A backend Airport Management System built using Spring Boot, Spring Data JPA, and PostgreSQL.
This project provides REST APIs to manage Airlines, Flights, Bookings, and Seat Reservations with proper exception handling.

🚀 Features
✈️ Airline Management

➡️Create airline

➡️Get all airlines

➡️Get airline by ID

➡️Delete airline

🛫 Flight Management

➡️Create flights under an airline

➡️Get all flights of an airline

➡️Get flight by ID

➡️Search flight by flight number

🎟️ Booking & Seat Management

Book seats for a flight

Prevent overbooking (InsufficientSeatsException)

Get bookings by passenger name

Automatic seat availability update

⚠️ Exception Handling

Custom exceptions (AirlineException, InsufficientSeatsException)

Global exception handling with meaningful error responses

🛠️ Tech Stack

➡️Java 21

➡️Spring Boot 4

➡️Spring Data JPA

➡️Hibernate

➡️PostgreSQL

➡️Maven

➡️Jackson (JSON)

➡️Lombok

Lombok

📂 Project Structure

airport-management-system
│
├── controller
│   ├── AirlineController
│   ├── FlightController
│   └── BookingController
│
├── service
│   ├── AirlineService
│   ├── FlightService
│   └── BookingService
│
├── service/impl
│
├── repository
│
├── entity
│   ├── Airline
│   ├── Flight
│   └── Booking
│
├── dto
│
├── mapper
│
├── exception
│
└── AirportManagementSystemApplication

🗄️ Database Configuration

PostgreSQL is used as the database.

application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/airport_management
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

🔗 API Endpoints
Airline APIs
Method	Endpoint	Description
POST	/api/airlines	Create airline
GET	/api/airlines	Get all airlines
GET	/api/airlines/{id}	Get airline by ID
DELETE	/api/airlines/{id}	Delete airline
Flight APIs
Method	Endpoint	Description
POST	/api/flights/{airlineId}/flights	Create flight
GET	/api/flights/{airlineId}/flights	Get flights by airline
GET	/api/flights/{flightId}	Get flight by ID
Booking APIs
Method	Endpoint	Description
POST	/api/bookings	Book seats
GET	/api/bookings/passenger/{name}	Get bookings by passenger

▶️ How to Run
1. Clone the repository
   git clone https://github.com/UtkarshPardhi/airport-management-system-SpringBoot-.git
2. Open in IntelliJ IDEA
3. Configure PostgreSQL database
4. Run:
   mvn spring-boot:run
5. Server starts at:
   http://localhost:8080

📌 Future Enhancements

➡️Authentication & Authorization (JWT)

➡️Swagger / OpenAPI documentation

➡️Pagination & sorting

➡️Seat class (Economy, Business)

➡️Payment integration

👨‍💻 Author
Utkarsh Pardhi
Java | Spring Boot | Backend Development

⭐ If you like this project

Give it a ⭐ on GitHub — it motivates a lot 😄

