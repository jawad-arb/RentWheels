package org.team.rentwheels.repositories.implementations;

import org.team.rentwheels.exceptions.CarAlreadyReservedException;
import org.team.rentwheels.models.Car;
import org.team.rentwheels.models.Customer;
import org.team.rentwheels.models.Reservation;
import org.team.rentwheels.models.ReservationDTO;
import org.team.rentwheels.repositories.ReservationRepository;
import org.team.rentwheels.utils.DatabaseOperations;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.team.rentwheels.queries.ReservationQuery.*;

public class ReservationRepositoryImpl implements ReservationRepository {

    DatabaseOperations dbOperations = new DatabaseOperations();
    Car car=new Car();
    Customer customer=new Customer();

    @Override
    public void addReservation(Reservation reservation) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(ADD_RESERVATION_QUEY);
        ps.setInt(1,reservation.getCar().getCarId());
        ps.setInt(2,reservation.getCustomer().getId());
        ps.setDate(3,reservation.getReservationDate());
        ps.setDate(4,reservation.getStartDate());
        ps.setDate(5,reservation.getEndDate());
        ps.setDouble(6,reservation.getTotalCost());
        ps.setString(7,reservation.getStatus());
        ps.setDouble(8,reservation.getAdvancedPrice());
        ps.executeUpdate();
    }

    @Override
    public void deleteReservation(int reservationId) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(DELETE_RESERVATION_BY_ID);
        ps.setInt(1,reservationId);
        ps.executeUpdate();
    }

    @Override
    public void updateReservation(int reservationId,Reservation updatedReservation) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(UPDATE_RESERVATION);
        ps.setInt(1,updatedReservation.getCar().getCarId());
        ps.setInt(2,updatedReservation.getCustomer().getId());
        ps.setDate(3,updatedReservation.getReservationDate());
        ps.setDate(4,updatedReservation.getStartDate());
        ps.setDate(5,updatedReservation.getEndDate());
        ps.setDouble(6,updatedReservation.getTotalCost());
        ps.setString(7,updatedReservation.getStatus());
        ps.setDouble(8,updatedReservation.getAdvancedPrice());
        ps.setInt(9,reservationId);
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
            car.setCarId(rs.getInt("car_id"));
            reservation.setCar(car);
            customer.setId(rs.getInt("customer_id"));
            reservation.setCustomer(customer);
            reservation.setReservationDate(rs.getDate("reservation_date"));
            reservation.setStartDate(rs.getDate("start_date"));
            reservation.setEndDate(rs.getDate("end_date"));
            reservation.setTotalCost(rs.getDouble("total_cost"));
            reservation.setStatus(rs.getString("status"));
            reservation.setAdvancedPrice(rs.getDouble("advanced_price"));
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
            car.setCarId(rs.getInt("car_id"));
            reservation.setCar(car);
            customer.setId(rs.getInt("customer_id"));
            reservation.setCustomer(customer);
            reservation.setReservationDate(rs.getDate("reservation_date"));
            reservation.setStartDate(rs.getDate("start_date"));
            reservation.setEndDate(rs.getDate("end_date"));
            reservation.setTotalCost(rs.getDouble("total_cost"));
            reservation.setStatus(rs.getString("status"));
            reservation.setAdvancedPrice(rs.getDouble("advanced_price"));
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
            car.setCarId(rs.getInt("car_id"));
            reservation.setCar(car);
            customer.setId(rs.getInt("customer_id"));
            reservation.setCustomer(customer);
            reservation.setReservationDate(rs.getDate("reservation_date"));
            reservation.setStartDate(rs.getDate("start_date"));
            reservation.setEndDate(rs.getDate("end_date"));
            reservation.setTotalCost(rs.getDouble("total_cost"));
            reservation.setStatus(rs.getString("status"));
            reservation.setAdvancedPrice(rs.getDouble("advanced_price"));
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

    @Override
    public List<ReservationDTO> getAllConfirmedReservation() throws SQLException {
        List<ReservationDTO> reservationDTOS=new ArrayList<>();
        PreparedStatement ps=dbOperations.setConnection(GET_ALL_RESERVATION_FOR_TABLE_VIEW);
        ps.setString(1,"Confirmed");
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            ReservationDTO reservationDTO=new ReservationDTO();
            reservationDTO.setCarName(rs.getString(1));
            reservationDTO.setCustomerName(rs.getString(2));
            reservationDTO.setReservationDate(rs.getDate(3));
            reservationDTO.setStartDate(rs.getDate(4));
            reservationDTO.setEndDate(rs.getDate(5));
            reservationDTOS.add(reservationDTO);
        }
        return reservationDTOS;
    }

    public void updateCarAvailability(int carId, Date startDate, Date endDate, boolean available) throws SQLException {
        java.util.Date systemDate =  java.util.Date.from(Instant.now());
        java.sql.Date sqlSystemDate = new java.sql.Date(systemDate.getTime());
        if (sqlSystemDate.after(startDate)) {
            available = false; // Set availability to false if date is past
        }
        try {
            // Prepare statements
            PreparedStatement updateStatement = dbOperations.setConnection(
                    "UPDATE Cars SET availability = ? WHERE car_id = ?"
            );
            PreparedStatement checkExistingReservationsStatement = dbOperations.setConnection(
                    "SELECT COUNT(*) FROM Reservations WHERE car_id = ? AND ((start_date BETWEEN ? AND ?) OR (end_date BETWEEN ? AND ?))"
            );

            // Update available flag
            updateStatement.setBoolean(1, available);
            updateStatement.setInt(2, carId);
            updateStatement.executeUpdate();

            // Check for existing reservations
            checkExistingReservationsStatement.setInt(1, carId);
            checkExistingReservationsStatement.setDate(2, new java.sql.Date(startDate.getTime()));
            checkExistingReservationsStatement.setDate(3, new java.sql.Date(endDate.getTime()));
            checkExistingReservationsStatement.setDate(4, new java.sql.Date(startDate.getTime()));
            checkExistingReservationsStatement.setDate(5, new java.sql.Date(endDate.getTime()));

            ResultSet resultSet = checkExistingReservationsStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                throw new CarAlreadyReservedException("Car already has reservations during the specified period.");
            }
        } catch (CarAlreadyReservedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param
     * @param
     * @param
     * @return
     * @Method (carPrice * NbrDays)-advancedPrice
     */
    @Override
    public double calculateTotaleCost(Reservation reservation) throws SQLException {
        double totalCost = 0.0;
        PreparedStatement ps=dbOperations.setConnection("SELECT r.*, c.price, DATEDIFF(r.end_date, r.start_date) AS NbrDays, (c.price * NbrDays) - r.advanced_price AS total_cost FROM Reservations r INNER JOIN Cars c ON r.car_id = c.car_id WHERE r.reservation_id = ?");
        ps.setInt(1, reservation.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalCost = rs.getDouble("total_cost");
        }
        return totalCost;
    }
}
