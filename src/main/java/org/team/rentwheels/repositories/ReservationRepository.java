package org.team.rentwheels.repositories;

import org.team.rentwheels.models.Reservation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReservationRepository {
    // add a reservation
    void addReservation(Reservation reservation);
    // delete a reservation
    void deleteReservation(int reservationId) throws SQLException;
    // update a reservation
    void updateReservation(Reservation updatedReservation) throws SQLException;
    // get a reservation by Id
    Reservation getReservationById(int reservationId) throws SQLException;
    //get All reservation by CarId
    List<Reservation> getAllReservationByCarId(int carId) throws SQLException;
    //get All reservation by Customer id
    List<Reservation> getAllReservationByCustomerId(int customerId) throws SQLException;
    //get the number of reservation by Customer id
    int getNumberOfReservationsByCustomerId(int customerId) throws SQLException;
    //Check if a particular car is available for
    // reservation within a given date range.
    boolean isCarAvailableForReservation(int carId, Date startDate, Date endDate);
    int getTotalNumberOfReservations();
    // get total Revenue From All the reservation
    double calculateTotalRevenueFromReservations();
    // return the number of days for the each reservation
    int calculateNumberOfDays(Reservation reservation);


}
