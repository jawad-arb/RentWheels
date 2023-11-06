package org.team.rentwheels.services;

import org.team.rentwheels.models.Reservation;
import org.team.rentwheels.repositories.ReservationRepository;
import org.team.rentwheels.repositories.implementations.ReservationRepositoryImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(){
        this.reservationRepository=new ReservationRepositoryImpl();
    }
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * @All the methods in service
     * @param reservation
     * @throws SQLException
     */

    void addReservation(Reservation reservation) throws SQLException{
        if(!isCarAvailableForReservation(reservation.getCarId(),reservation.getStartDate(),reservation.getEndDate()))
            throw new RuntimeException();

        // if the car is Available add the reservation to database
        // check if the Customer in the BlackList or not
        // if a Customer reserved 3 time give him promotion for 15%

        // car not available in the period of time
        // throw new CarNotAvailableException("Car is not available for the requested period");

    }
    void deleteReservation(int reservationId) throws SQLException{
//check if the reservation exists
        Reservation existingReservation=getReservationById(reservationId);
        if(existingReservation!=null){
            // delete from database
        }else{
            // throw exception ReservationNotFound
        }
    }
    void updateReservation(Reservation updatedReservation) throws SQLException{
        //check if the reservation Exists
        Reservation existingReservation=getReservationById(updatedReservation.getId());
        if(existingReservation!=null){
            // update the reservation fields
        }else{
            //throw Exception ReservationNotFound
        }
    }
    public boolean isCarAvailableForReservation(int carId, Date startDate, Date endDate) throws SQLException{
        // fetch all Reservations with the same carId
        List<Reservation> reservationsForCar=getAllReservationByCarId(carId);
        // check the dates if the car available in the start date
    }
    Reservation getReservationById(int reservationId) throws SQLException{
        return this.reservationRepository.getReservationById(reservationId);
    }
    List<Reservation> getAllReservationByCarId(int carId) throws SQLException{
        return this.reservationRepository.getAllReservationByCarId(carId);
    }
    List<Reservation> getAllReservationByCustomerId(int customerId) throws SQLException{
        return this.reservationRepository.getAllReservationByCustomerId(customerId);
    }

    public int getNumberOfReservationsByCustomerId(int customerId) throws SQLException{
        return this.reservationRepository.getNumberOfReservationsByCustomerId(customerId);
    }
    public int getTotalNumberOfReservations() throws SQLException{
        return this.reservationRepository.getTotalNumberOfReservations();
    }
    public double calculateTotalRevenueFromReservations() throws SQLException{
        return this.reservationRepository.calculateTotalRevenueFromReservations();
    }
    public int calculateNumberOfDays(Reservation reservation) {
        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();
        long difference = endDate.getTime() - startDate.getTime();
        int numberOfDays = (int) (difference / (1000 * 60 * 60 * 24)) + 1;
        return numberOfDays;
    }

}
