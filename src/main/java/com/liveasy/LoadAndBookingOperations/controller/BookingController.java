package com.liveasy.LoadAndBookingOperations.controller;

import com.liveasy.LoadAndBookingOperations.dto.BookingRequest;
import com.liveasy.LoadAndBookingOperations.entity.Booking;
import com.liveasy.LoadAndBookingOperations.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings(@RequestParam(required = false) String shipperId,
                                        @RequestParam(required = false) String transporterId) {
        return bookingService.getAllBookings(shipperId, transporterId);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingById(@PathVariable UUID bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable UUID bookingId, @RequestBody Booking updatedBooking) {
        return bookingService.updateBooking(bookingId, updatedBooking);
    }

    @PatchMapping("/accept/{bookingId}")
    public ResponseEntity<?> acceptBooking(@PathVariable UUID bookingId){
        return bookingService.acceptBooking(bookingId);
    }

    @PatchMapping("/reject/{bookingId}")
    public ResponseEntity<?> rejectBooking(@PathVariable UUID bookingId){
        return bookingService.rejectBooking(bookingId);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable UUID bookingId) {
        return bookingService.deleteBooking(bookingId);
    }
}
