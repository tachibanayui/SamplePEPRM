package com.example.samplepe;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Booking")
public class BookingEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "bookingId")
    int id;

    @ColumnInfo(name = "bookingCustomerName")
    String customerName;

    @ColumnInfo(name = "bookingCustomerDate")
    String date;

    @ColumnInfo(name = "bookingCustomerPrice")
    double price;

    @ColumnInfo(name = "bookingCustomerQuantity")
    int quantity;

    @ColumnInfo(name = "bookingCustomerSrvName")
    String serviceName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getTotalValue() {
        return getPrice() * getQuantity();
    }

    public BookingEntity(int id, String customerName, String date, double price, int quantity, String serviceName) {
        this.id = id;
        this.customerName = customerName;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.serviceName = serviceName;
    }

    public BookingEntity() {
    }
}
