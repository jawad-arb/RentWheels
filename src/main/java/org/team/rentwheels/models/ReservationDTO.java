package org.team.rentwheels.models;

import java.sql.Date;

public class ReservationDTO {
    private int id;
    private String carName;
    private String customerName;
    private Date reservationDate;
    private Date startDate;
    private Date endDate;
    private Double totalCost;
    private Double advancedPrice;
    private String status;

    public Double getAdvancedPrice() {
        return advancedPrice;
    }

    public void setAdvancedPrice(Double advancedPrice) {
        this.advancedPrice = advancedPrice;
    }

    public ReservationDTO(int id, String carName, String customerName, Date reservationDate, Date startDate, Date endDate, Double totalCost, Double advancedPrice, String status) {
        this.id = id;
        this.carName = carName;
        this.customerName = customerName;
        this.reservationDate = reservationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.advancedPrice = advancedPrice;
        this.status = status;
    }

    public ReservationDTO(String carName, String customerName, Date reservationDate, Date startDate, Date endDate, Double totalCost, Double advancedPrice, String status) {
        this.carName = carName;
        this.customerName = customerName;
        this.reservationDate = reservationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.advancedPrice = advancedPrice;
        this.status = status;
    }

    public ReservationDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
