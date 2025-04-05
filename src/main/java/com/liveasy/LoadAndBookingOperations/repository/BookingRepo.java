package com.liveasy.LoadAndBookingOperations.repository;

import com.liveasy.LoadAndBookingOperations.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepo extends JpaRepository<Booking, UUID> {
    List<Booking> findByTransporterId(String transporterId);

    List<Booking> findByLoad_ShipperId(String shipperId);
    List<Booking> findByLoad_ShipperIdAndTransporterId(String shipperId, String transporterId);

}
