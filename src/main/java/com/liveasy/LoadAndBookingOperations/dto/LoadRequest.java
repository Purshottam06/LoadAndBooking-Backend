package com.liveasy.LoadAndBookingOperations.dto;

import com.liveasy.LoadAndBookingOperations.entity.Facility;

public class LoadRequest {
    private String shipperId;
    private Facility facility;
    private String productType;
    private String truckType;

    @Override
    public String toString() {
        return "LoadRequest{" +
                "shipperId='" + shipperId + '\'' +
                ", facility=" + facility +
                ", productType='" + productType + '\'' +
                ", truckType='" + truckType + '\'' +
                ", noOfTrucks=" + noOfTrucks +
                ", weight=" + weight +
                ", comment='" + comment + '\'' +
                '}';
    }

    private int noOfTrucks;
    private double weight;
    private String comment;

    public String getShipperId() {
        return shipperId;
    }

    public Facility getFacility() {
        return facility;
    }

    public String getProductType() {
        return productType;
    }

    public String getTruckType() {
        return truckType;
    }

    public int getNoOfTrucks() {
        return noOfTrucks;
    }

    public double getWeight() {
        return weight;
    }

    public String getComment() {
        return comment;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public void setNoOfTrucks(int noOfTrucks) {
        this.noOfTrucks = noOfTrucks;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
