# LoadAndBooking-Backend
This is a Spring Boot application to manage load and booking operations efficiently. It provides RESTful APIs for creating, reading, updating, deleting (CRUD), as well as accepting and rejecting bookings. It also manages load details such as source, destination, quantity, and truck type to facilitate smooth logistics operations. Booking operations are dependent on existing loads, meaning a booking can only be created for a valid load.

Tech Stack
Java 17

Spring Boot 3.4.4

PostgreSQL

Maven

Lombok

ModelMapper

Setup Instructions
  1.Clone the repository
    git clone https://github.com/Purshottam06/LoadAndBooking-Backend.git
    cd LoadAndBookingOperations
    
  2.Configure PostgreSQL Database
  Update your application.properties  with your database config:
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
    spring.datasource.username=your_user
    spring.datasource.password=your_password
    
  3.Build the project
    mvn clean install
  4. Run the application
    mvn spring-boot:run

API Endpoints

  Load Management
      POST  /api/load → Create a new load
      GET  /api/load → Fetch loads (filter by shipperId, truckType, etc.)
      GET  /api/load/{loadId} → Get load details
      PUT  /api/load/{loadId} → Update load details
      DELETE  /api/load/{loadId} → Delete a load
  Booking Management
      POST /api/booking → Create a new booking
      GET  /api/booking → Fetch bookings (filter by shipperId, transporterId)
      GET  /api/booking/{bookingId} → Get booking details
      PUT  /api/booking/{bookingId} → Update booking details
      DELETE /api/booking/{bookingId} → Delete a booking
      PATCH /api/booking/accept/{bookingId} → Accept a booking
      PATC /api/booking/reject/{bookingId} → Reject a booking
Rules:
   → status should default to POSTED when a load is created.
   → When a booking is made (POST /booking), the status should change to BOOKED.
   → If a booking is deleted, the load status should be CANCELLED.
   → A booking should not be created if the load is already CANCELLED.
   → When a booking is accepted, update the status to ACCEPTED.

Assumptions

  Accept or Reject Bookings:
    Use the /accept/{bookingId} endpoint when you want to accept a booking. 
    Use the /reject/{bookingId} endpoint when you want to reject a booking.
  
  Filtering Loads or Bookings:
    You can filter loads by shipperId and truckType, and 
    bookings by shipperId and transporterId using query parameters in GET requests.
  
  Booking Depends on Load:
    A booking cannot exist without a load. Ensure the load exists before creating a booking linked to it.
  
  UUID Format:
    All resource identifiers (loadId, bookingId) are UUIDs

