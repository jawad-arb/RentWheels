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
    public void addReservation(Reservation reservation) {
        if(isCarAvailableForReservation(reservation.getCarId(),reservation.getStartDate(),reservation.getEndDate())){
            // if the car is Available add the reservation to database
            // if a Customer reserved 3 time give him promotion for 15%
        }else{
            // car not available in the period of time
            // throw new CarNotAvailableException("Car is not available for the requested period");

        }

    }

    @Override
    public void deleteReservation(int reservationId) throws SQLException {
        //check if the reservation exists
        Reservation existingReservation=getReservationById(reservationId);
        if(existingReservation!=null){
            // delete from database
        }else{
            // throw exception ReservationNotFound
        }
    }

    @Override
    public void updateReservation(Reservation updatedReservation) throws SQLException {
        //check if the reservation Exists
        Reservation existingReservation=getReservationById(updatedReservation.getId());
        if(existingReservation!=null){
            // update the reservation fields
        }else{
            //throw Exception ReservationNotFound
        }
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
        }else{
            return 0;
        }
    }

    @Override
    public boolean isCarAvailableForReservation(int carId, Date startDate, Date endDate) {
        return false;
    }

    @Override
    public int getTotalNumberOfReservations() {
        return 0;
    }

    @Override
    public double calculateTotalRevenueFromReservations() {
        return 0;
    }

    @Override
    public int calculateNumberOfDays(Reservation reservation) {
        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();

        // Calculate the difference between the start date and the end date.
        long difference = endDate.getTime() - startDate.getTime();

        // Add one to the difference to account for the first day of the reservation.
        int numberOfDays = (int) (difference / (1000 * 60 * 60 * 24)) + 1;

        return numberOfDays;
    }


    public static void main(String[] args) throws SQLException {
        ReservationRepository reservationRepository=new ReservationRepositoryImpl();
//        Reservation reservation=new Reservation();
//        List<Reservation> reservations=reservationRepository.getAllReservationByCustomerId(6);
        System.out.println(reservationRepository.getNumberOfReservationsByCustomerId(6));
    }


}
