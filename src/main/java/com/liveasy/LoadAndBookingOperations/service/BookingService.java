package com.liveasy.LoadAndBookingOperations.service;

import com.liveasy.LoadAndBookingOperations.dto.BookingRequest;
import com.liveasy.LoadAndBookingOperations.entity.Booking;
import com.liveasy.LoadAndBookingOperations.entity.Load;
import com.liveasy.LoadAndBookingOperations.enums.BookingStatus;
import com.liveasy.LoadAndBookingOperations.enums.LoadStatus;
import com.liveasy.LoadAndBookingOperations.repository.BookingRepo;
import com.liveasy.LoadAndBookingOperations.repository.LoadRepo;
import com.liveasy.LoadAndBookingOperations.utility.LoggerClass;
import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Service
public class BookingService {
    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    LoadRepo loadRepo;

    ModelMapper modelMapper=new ModelMapper();

//    private static final Logger log = LoggerFactory.getLogger(BookingService.class);
    Logger log= LoggerClass.getLogger("BookingService.class");
    @Transactional
    public ResponseEntity<?> createBooking(BookingRequest booking) {
        log.info("creating Booking"+booking);
        try {
            Optional<Load> load = loadRepo.findById(booking.getLoadId());
            if (load.isEmpty()) {
                log.warn("load id is invalid"+booking.getLoadId());
                return ResponseEntity.badRequest().body("Invalid loadId");
            }

            LoadStatus loadStatus = load.get().getStatus();
            if (loadStatus == LoadStatus.CANCELLED) {
                log.warn("load status is:"+loadStatus);
                return ResponseEntity.badRequest().body("A booking should not be created if the load is already CANCELLED");
            }
            Booking b=new Booking();

            b.setLoad(load.get());
            b.setComment(booking.getComment());
            b.setProposedRate(booking.getProposedRate());
            b.setTransporterId(booking.getTransporterId());
            log.info("Persisting booking information"+booking);
            bookingRepo.save(b);

//          When a booking is made (POST /booking), the status should change to BOOKED.
            load.get().setStatus(LoadStatus.BOOKED);
            log.info("Making change in load status"+load);

            loadRepo.save(load.get());

            return ResponseEntity.ok().body("Booking is created");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    public ResponseEntity<?> getAllBookings(String shipperId, String transporterId) {
        log.info("filtering booking: shipperId:-"+shipperId+" ,transporterId:-"+transporterId);
        try {
            List<Booking> bookings;

            // Filtering logic
            if (shipperId != null && transporterId != null) {
                bookings = bookingRepo.findByLoad_ShipperIdAndTransporterId(shipperId, transporterId);
                log.info("filtering based on shipperId and transporterId"+bookings);
            } else if (shipperId != null) {
                bookings = bookingRepo.findByLoad_ShipperId(shipperId);
                log.info("filtering based on shipperId"+bookings);
            } else if (transporterId != null) {
                bookings = bookingRepo.findByTransporterId(transporterId);
                log.info("filtering based on transporterId"+bookings);
            } else {
                bookings = bookingRepo.findAll(); // No filter, return all bookings
                log.info("No filter, return all bookings"+bookings);
            }

            if (bookings.isEmpty()) {
                log.warn("No bookings found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No bookings found");
            }

            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            log.error("Exception in getAllBookings: "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error retrieving bookings: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getBookingById(UUID bookingId) {
        log.info("finding booking based on bookingId"+bookingId);
        try {
            Optional<Booking> booking = bookingRepo.findById(bookingId);
            if(booking.isEmpty()){
                log.warn("bookingId is"+bookingId);
                return ResponseEntity.notFound().build();
            }
            log.info("booking based on bookingId"+booking.get());
            return ResponseEntity.status(HttpStatus.FOUND).body(booking.get());
        }catch (Exception e){
            log.error("Exception in getBookingById: "+e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> updateBooking(UUID bookingId, Booking updatedBooking) {
        log.info("updating booking"+bookingId+"\n"+updatedBooking);
        try {
            Optional<Booking> booking = bookingRepo.findById(bookingId);
            if(booking.isEmpty()){
                log.warn("bookingId is:"+bookingId);
                return ResponseEntity.notFound().build();
            }
            modelMapper.map(updatedBooking,booking.get());
            log.info("Persisting booking updated details"+booking.get());
            bookingRepo.save(booking.get());
            return ResponseEntity.ok().body("Booking updated");
        }catch (Exception e){
            log.error("Exception in updateBooking: "+e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> acceptBooking(UUID bookingId){
        log.info("acceptBooking: "+bookingId);
        try{
            Optional<Booking> booking=bookingRepo.findById(bookingId);
            if(booking.isEmpty()){
                log.warn("bookingId is:"+bookingId);
                return ResponseEntity.notFound().build();
            }
            booking.get().setStatus(BookingStatus.ACCEPTED);
            log.info("persisting acceptBooking"+booking.get());
            bookingRepo.save(booking.get());

            return ResponseEntity.ok().body("booking accepted");
        }catch (Exception e){
            log.error("Exception in acceptBooking"+e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> rejectBooking(UUID bookingId){
        log.info("rejectBooking: "+bookingId);
        try{
            Optional<Booking> booking=bookingRepo.findById(bookingId);
            if(booking.isEmpty()){
                log.warn("bookingId is:"+bookingId);
                return ResponseEntity.notFound().build();
            }
            booking.get().setStatus(BookingStatus.REJECTED);
            log.info("persisting rejectBooking"+booking.get());
            bookingRepo.save(booking.get());

            return ResponseEntity.ok().body("booking rejected");
        }catch (Exception e){
            log.error("Exception in rejectBooking"+e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<String> deleteBooking(UUID bookingId) {
        log.info("deleteBooking: bookingId"+bookingId);
        try {
//            If a booking is deleted, the load status should be CANCELLED.
            Optional<Booking> booking=bookingRepo.findById(bookingId);
            Load load=booking.get().getLoad();
            load.setStatus(LoadStatus.CANCELLED);
            log.info("changing load status to CANCELLED and persisting load details");
            loadRepo.save(load);

            bookingRepo.deleteById(bookingId);
            return ResponseEntity.ok("Booking deleted successfully");
        }catch (Exception e){
            log.error("Exception in deleteBooking"+e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
