package org.team.rentwheels.models;

import java.sql.Date;

public class BlackList {
    private int id;
    private int customerId;
    private int carId;
    private Date startDate;
    private Date endDate;
    private String reason;

    public BlackList() {
    }

    public BlackList(int id,
                     int customerId,
                     int carId,
                     Date startDate,
                     Date endDate,
                     String reason) {
        this.id = id;
        this.customerId = customerId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    public BlackList(int customerId,
                     int carId,
                     Date startDate,
                     Date endDate,
                     String reason) {
        this.customerId = customerId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", carId=" + carId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reason='" + reason + '\'' +
                '}';
    }
}
