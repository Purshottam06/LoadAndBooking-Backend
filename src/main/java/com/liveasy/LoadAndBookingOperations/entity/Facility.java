package com.liveasy.LoadAndBookingOperations.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.sql.Timestamp;

@Embeddable
@Data
public class Facility {
    private String loadingPoint;
    private String unloadingPoint;
    private Timestamp loadingDate;
    private Timestamp unloadingDate;

    public String getLoadingPoint() {
        return loadingPoint;
    }

    public void setLoadingPoint(String loadingPoint) {
        this.loadingPoint = loadingPoint;
    }

    public String getUnloadingPoint() {
        return unloadingPoint;
    }

    public void setUnloadingPoint(String unloadingPoint) {
        this.unloadingPoint = unloadingPoint;
    }

    public Timestamp getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(Timestamp loadingDate) {
        this.loadingDate = loadingDate;
    }

    public Timestamp getUnloadingDate() {
        return unloadingDate;
    }

    public void setUnloadingDate(Timestamp unloadingDate) {
        this.unloadingDate = unloadingDate;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "loadingPoint='" + loadingPoint + '\'' +
                ", unloadingPoint='" + unloadingPoint + '\'' +
                ", loadingDate=" + loadingDate +
                ", unloadingDate=" + unloadingDate +
                '}';
    }
}