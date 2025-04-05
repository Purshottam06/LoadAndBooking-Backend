# LoadAndBooking-Backend

This is a Spring Boot application to manage **load** and **booking** operations efficiently.  
It provides RESTful APIs for creating, reading, updating, and deleting (CRUD), as well as accepting and rejecting bookings.  
It also manages load details such as source, destination, quantity, and truck type to facilitate smooth logistics operations.  

**Booking operations are dependent on existing loads**, meaning a booking can only be created for a valid load.

---

## 🛠 Tech Stack

- Java 17  
- Spring Boot 3.4.4  
- PostgreSQL  
- Maven  
- Lombok  
- ModelMapper  

---

## 🚀 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/Purshottam06/LoadAndBooking-Backend.git
   cd LoadAndBookingOperations
   ```

2. **Configure PostgreSQL Database**  
   Update your `application.properties` with your database config:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

---

## 📦 API Endpoints

### Load Management

| Method | Endpoint                 | Description                                 |
|--------|--------------------------|---------------------------------------------|
| POST   | `/api/load`              | Create a new load                           |
| GET    | `/api/load`              | Fetch all loads (filter by `shipperId`, `truckType`) |
| GET    | `/api/load/{loadId}`     | Get load details                            |
| PUT    | `/api/load/{loadId}`     | Update load details                         |
| DELETE | `/api/load/{loadId}`     | Delete a load                               |

---

### Booking Management

| Method | Endpoint                           | Description                    |
|--------|------------------------------------|--------------------------------|
| POST   | `/api/booking`                     | Create a new booking           |
| GET    | `/api/booking`                     | Fetch bookings (filter by `shipperId`, `transporterId`) |
| GET    | `/api/booking/{bookingId}`         | Get booking details            |
| PUT    | `/api/booking/{bookingId}`         | Update booking details         |
| DELETE | `/api/booking/{bookingId}`         | Delete a booking               |
| PATCH  | `/api/booking/accept/{bookingId}`  | Accept a booking               |
| PATCH  | `/api/booking/reject/{bookingId}`  | Reject a booking               |

---

## 📌 Rules

- Load `status` should default to `POSTED` when created.  
- When a booking is made (`POST /booking`), its status should be set to `BOOKED`.  
- If a booking is deleted, the associated load’s status should become `CANCELLED`.  
- A booking **should not** be created if the load is already `CANCELLED`.  
- When a booking is accepted, its status becomes `ACCEPTED`.

---

## ✅ Assumptions

- **Accept or Reject Bookings**:  
  Use `/accept/{bookingId}` to **accept** a booking, and  
  `/reject/{bookingId}` to **reject** a booking.

- **Filtering Loads or Bookings**:  
  Use query parameters such as `shipperId`, `truckType` for loads, and  
  `shipperId`, `transporterId` for bookings when using GET requests.

- **Booking Depends on Load**:  
  A booking must be linked to an existing load.

- **UUID Format**:  
  All IDs (`loadId`, `bookingId`) are UUIDs.

---
