package org.team.rentwheels.models;

import java.sql.Date;
import java.util.Arrays;

public class Car {
    private int carId;
    private int brandId;
    private String model;
    private double price;
    private boolean isAvailable;
    private byte[] car_image;
    private String maintenance_status;
    private Date last_maintenance_date;

    public Car() {
    }

    public Car(int brandId,
               String model,
               double price,
               boolean isAvailable,
               byte[] car_image,
               String maintenance_status,
               Date last_maintenance_date) {
        this.brandId = brandId;
        this.model = model;
        this.price = price;
        this.isAvailable = isAvailable;
        this.car_image = car_image;
        this.maintenance_status = maintenance_status;
        this.last_maintenance_date = last_maintenance_date;
    }

    public Car(int carId,
               int brandId,
               String model,
               float price,
               boolean isAvailable,
               byte[] car_image,
               String maintenance_status,
               Date last_maintenance_date) {
        this.carId = carId;
        this.brandId = brandId;
        this.model = model;
        this.price = price;
        this.isAvailable = isAvailable;
        this.car_image = car_image;
        this.maintenance_status = maintenance_status;
        this.last_maintenance_date = last_maintenance_date;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public byte[] getCar_image() {
        return car_image;
    }

    public void setCar_image(byte[] car_image) {
        this.car_image = car_image;
    }

    public String getMaintenance_status() {
        return maintenance_status;
    }

    public void setMaintenance_status(String maintenance_status) {
        this.maintenance_status = maintenance_status;
    }

    public Date getLast_maintenance_date() {
        return last_maintenance_date;
    }

    public void setLast_maintenance_date(Date last_maintenance_date) {
        this.last_maintenance_date = last_maintenance_date;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", brandId=" + brandId +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", car_image=" + Arrays.toString(car_image) +
                ", maintenance_status='" + maintenance_status + '\'' +
                ", last_maintenance_date=" + last_maintenance_date +
                '}';
    }

}
