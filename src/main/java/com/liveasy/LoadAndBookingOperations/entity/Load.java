package com.liveasy.LoadAndBookingOperations.entity;

import com.liveasy.LoadAndBookingOperations.enums.LoadStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "loads")
public class Load {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String shipperId;

    @Embedded
    private Facility facility;

    private String productType;

    private String truckType;

    private int noOfTrucks;

    private double weight;

    private String comment;

    private Timestamp datePosted=new Timestamp(System.currentTimeMillis());

    @Enumerated(value =EnumType.STRING)
    private LoadStatus status=LoadStatus.POSTED;

    public UUID getId() {
        return id;
    }

    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public int getNoOfTrucks() {
        return noOfTrucks;
    }

    public void setNoOfTrucks(int noOfTrucks) {
        this.noOfTrucks = noOfTrucks;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Timestamp datePosted) {
        this.datePosted = datePosted;
    }

    public LoadStatus getStatus() {
        return status;
    }

    public void setStatus(LoadStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Load{" +
                "id=" + id +
                ", shipperId='" + shipperId + '\'' +
                ", facility=" + facility +
                ", productType='" + productType + '\'' +
                ", truckType='" + truckType + '\'' +
                ", noOfTrucks=" + noOfTrucks +
                ", weight=" + weight +
                ", comment='" + comment + '\'' +
                ", datePosted=" + datePosted +
                ", status=" + status +
                '}';
    }
}

/*
{
  "id": "UUID",
  "shipperId": "String",
  "facility": {
    "loadingPoint": "String",
    "unloadingPoint": "String",
    "loadingDate": "Timestamp",
    "unloadingDate": "Timestamp"
  },
  "productType": "String",
  "truckType": "String",
  "noOfTrucks": "int",
  "weight": "double",
  "comment": "String",
  "datePosted": "Timestamp",
  "status": "POSTED | BOOKED | CANCELLED"
}
 */
