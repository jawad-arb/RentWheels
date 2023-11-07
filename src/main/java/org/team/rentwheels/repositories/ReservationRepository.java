package org.team.rentwheels.repositories;

import org.team.rentwheels.models.Reservation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReservationRepository {


    /**
     * add a reservation
     * @param reservation
     * @throws SQLException
     */
    void addReservation(Reservation reservation) throws SQLException;
    void deleteReservation(int reservationId) throws SQLException;
    void updateReservation(int reservationId,Reservation updatedReservation) throws SQLException;
    Reservation getReservationById(int reservationId) throws SQLException;
    List<Reservation> getAllReservationByCarId(int carId) throws SQLException;
    List<Reservation> getAllReservationByCustomerId(int customerId) throws SQLException;
    int getNumberOfReservationsByCustomerId(int customerId) throws SQLException;
    int getTotalNumberOfReservations() throws SQLException;
    double calculateTotalRevenueFromReservations() throws SQLException;
}
