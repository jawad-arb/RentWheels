package org.team.rentwheels.models;

import java.sql.Date;

public class Reservation {
    private int id;
    private int carId;
    private int customerId;
    private Date reservationDate;
    private Date startDate;
    private Date endDate;
    private Double totalCost;
    private String status;
    public Reservation(){

    }

    public Reservation(int id,
                       int carId,
                       int customerId,
                       Date reservationDate,
                       Date startDate,
                       Date endDate,
                       Double totalCost,
                       String status) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.reservationDate = reservationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = status;
    }

    public Reservation(int carId,
                       int customerId,
                       Date reservationDate,
                       Date startDate,
                       Date endDate,
                       Double totalCost,
                       String status) {
        this.carId = carId;
        this.customerId = customerId;
        this.reservationDate = reservationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", carId=" + carId +
                ", customerId=" + customerId +
                ", reservationDate=" + reservationDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalCost=" + totalCost +
                ", status='" + status + '\'' +
                '}';
    }

}
