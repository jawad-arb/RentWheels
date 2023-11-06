package org.team.rentwheels.repositories.implementations;

import org.team.rentwheels.models.Reservation;
import org.team.rentwheels.repositories.ReservationRepository;
import org.team.rentwheels.utils.DatabaseOperations;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.team.rentwheels.queries.ReservationQuery.*;

public class ReservationRepositoryImpl implements ReservationRepository {

    DatabaseOperations dbOperations = new DatabaseOperations();

    @Override
    public void addReservation(Reservation reservation) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(ADD_RESERVATION_QUEY);
        ps.setInt(1,reservation.getCarId());
        ps.setInt(2,reservation.getCustomerId());
        ps.setDate(3,reservation.getReservationDate());
        ps.setDate(4,reservation.getStartDate());
        ps.setDate(5,reservation.getEndDate());
        ps.setDouble(6,reservation.getTotalCost());
        ps.setString(7,reservation.getStatus());
        ps.executeUpdate();
    }

    @Override
    public void deleteReservation(int reservationId) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(DELETE_RESERVATION_BY_ID);
        ps.setInt(1,reservationId);
        ps.executeUpdate();
    }

    @Override
    public void updateReservation(Reservation updatedReservation) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(UPDATE_RESERVATION);
        ps.setInt(1,updatedReservation.getCarId());
        ps.setInt(2,updatedReservation.getCustomerId());
        ps.setDate(3,updatedReservation.getReservationDate());
        ps.setDate(4,updatedReservation.getStartDate());
        ps.setDate(5,updatedReservation.getEndDate());
        ps.setDouble(6,updatedReservation.getTotalCost());
        ps.setString(7,updatedReservation.getStatus());
        ps.executeUpdate();
    }

    @Override
    public Reservation getReservationById(int reservationId) throws SQLException {
        Reservation reservation=new Reservation();
        PreparedStatement ps=dbOperations.setConnection(GET_RESERVATION_BY_ID);
        ps.setInt(1,reservationId);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            reservation.setId(rs.getInt("reservation_id"));
            reservation.setCarId(rs.getInt("car_id"));
            reservation.setCustomerId(rs.getInt("customer_id"));
            reservation.setReservationDate(rs.getDate("reservation_date"));
            reservation.setStartDate(rs.getDate("start_date"));
            reservation.setEndDate(rs.getDate("end_date"));
            reservation.setTotalCost(rs.getDouble("total_cost"));
            reservation.setStatus(rs.getString("status"));
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAllReservationByCarId(int carId) throws SQLException {
        List<Reservation> reservations=new ArrayList<>();
        PreparedStatement ps=dbOperations.setConnection(GET_RESERVATIONS_BY_CAR_ID);
        ps.setInt(1,carId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Reservation reservation=new Reservation();
            reservation.setId(rs.getInt("reservation_id"));
            reservation.setCarId(rs.getInt("car_id"));
            reservation.setCustomerId(rs.getInt("customer_id"));
            reservation.setReservationDate(rs.getDate("reservation_date"));
            reservation.setStartDate(rs.getDate("start_date"));
            reservation.setEndDate(rs.getDate("end_date"));
            reservation.setTotalCost(rs.getDouble("total_cost"));
            reservation.setStatus(rs.getString("status"));
            reservations.add(reservation);
        }
        return reservations;
    }

    @Override
    public List<Reservation> getAllReservationByCustomerId(int customerId) throws SQLException {
        List<Reservation> reservations=new ArrayList<>();
        PreparedStatement ps=dbOperations.setConnection(GET_RESERVATIONS_BY_CUSTOMER_ID);
        ps.setInt(1,customerId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Reservation reservation=new Reservation();
            reservation.setId(rs.getInt("reservation_id"));
            reservation.setCarId(rs.getInt("car_id"));
            reservation.setCustomerId(rs.getInt("customer_id"));
            reservation.setReservationDate(rs.getDate("reservation_date"));
            reservation.setStartDate(rs.getDate("start_date"));
            reservation.setEndDate(rs.getDate("end_date"));
            reservation.setTotalCost(rs.getDouble("total_cost"));
            reservation.setStatus(rs.getString("status"));
            reservations.add(reservation);
        }
        return reservations;
    }

    @Override
    public int getNumberOfReservationsByCustomerId(int customerId) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(GET_COUNT_RESERVATION_BY_CUSTOMER_ID);
        ps.setInt(1,customerId);
        ResultSet rs=ps.executeQuery();
        if (rs.next()){
            return rs.getInt(1);
        }
            return 0;
    }

    @Override
    public boolean isCarAvailableForReservation(int carId, Date startDate, Date endDate) throws SQLException {
        // fetch all Reservations with the same carId
        List<Reservation> reservationsForCar=getAllReservationByCarId(carId);
        // check the dates if the car available in the start date

        return false;
    }

    @Override
    public int getTotalNumberOfReservations() throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(GET_TOTAL_NUMBER_OF_RESERVATIONS);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
            return 0;
    }

    @Override
    public double calculateTotalRevenueFromReservations() throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(CALCULATE_TOTAL_REVENUE_FROM_RESERVATIONS);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            return rs.getDouble(1);
        }
        return 0;
    }



}
