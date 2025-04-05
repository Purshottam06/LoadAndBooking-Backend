package com.liveasy.LoadAndBookingOperations.dto;

import com.liveasy.LoadAndBookingOperations.entity.Load;

import java.util.UUID;

public class BookingRequest {
    private UUID loadId;


    private String transporterId;

    private double proposedRate;

    private String comment;

    public UUID getLoadId() {
        return loadId;
    }

    public void setLoadId(UUID loadId) {
        this.loadId = loadId;
    }

    public String getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(String transporterId) {
        this.transporterId = transporterId;
    }

    public double getProposedRate() {
        return proposedRate;
    }

    public void setProposedRate(double proposedRate) {
        this.proposedRate = proposedRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "loadId=" + loadId +
                ", transporterId='" + transporterId + '\'' +
                ", proposedRate=" + proposedRate +
                ", comment='" + comment + '\'' +
                '}';
    }
}
