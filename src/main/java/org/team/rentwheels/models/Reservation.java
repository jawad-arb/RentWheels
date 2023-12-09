package org.team.rentwheels.models;

import java.sql.Date;

public class Reservation {
    private int id;
    private Car car;
    private Customer customer;
    private Date reservationDate;
    private Date startDate;
    private Date endDate;
    private Double totalCost;
    private String status;

    public Reservation() {
    }

    public Reservation(int id,
                       Car car,
                       Customer customer,
                       Date reservationDate,
                       Date startDate,
                       Date endDate,
                       Double totalCost,
                       String status) {
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.reservationDate = reservationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = status;
    }

    public Reservation(Car car,
                       Customer customer,
                       Date reservationDate,
                       Date startDate,
                       Date endDate,
                       Double totalCost,
                       String status) {
        this.car = car;
        this.customer = customer;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}
