package org.team.rentwheels.services;

import org.team.rentwheels.models.Reservation;
import org.team.rentwheels.repositories.ReservationRepository;
import org.team.rentwheels.repositories.implementations.ReservationRepositoryImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private static final double discount=15;
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
            throw new RuntimeException("Car is not available for the requested period");
        if(getNumberOfReservationsByCustomerId(reservation.getCustomerId())>=3){
            double discountedCost = applyPromotion(reservation, discount);
            reservation.setTotalCost(discountedCost);
        }
        reservationRepository.addReservation(reservation);
    }
    void deleteReservation(int reservationId) throws SQLException,RuntimeException{
        Reservation existingReservation=getReservationById(reservationId);
        if (existingReservation==null)
            throw new RuntimeException("Reservation Not Found");
        this.reservationRepository.deleteReservation(reservationId);
    }

    /**
     *
     * @param reservationId
     * @param updatedReservation
     * @throws SQLException
     * @Check if the reservation Exists
     */
    void updateReservation(int reservationId,Reservation updatedReservation) throws SQLException{
        Reservation existingReservation=getReservationById(reservationId);
        if (existingReservation==null)
            throw new RuntimeException("Reservation Not Found");
        this.reservationRepository.updateReservation(reservationId,updatedReservation);
    }
    /**
     *
     * @param carId
     * @param startDate
     * @param endDate
     * @return true--> carAvailable
     * @throws SQLException
     */
    public boolean isCarAvailableForReservation(int carId, Date startDate, Date endDate) throws SQLException{
        List<Reservation> reservationsForCar=getAllReservationByCarId(carId);
        for (Reservation reservation:reservationsForCar){
            if (doDatesOverLap(startDate,endDate,reservation.getStartDate(),reservation.getEndDate()))
                return false;
        }
        return true;
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

    /**
     *
     * @param startDate1
     * @param endDate1
     * @param startDate2
     * @param endDate2
     * @return true the Dates are OverLapes | false the Dates are not OverLapes so we are good to go
     */
    public boolean doDatesOverLap(Date startDate1, Date endDate1,Date startDate2, Date endDate2){
        return startDate1.before(endDate2) && startDate2.before(endDate1);
    }

    /**
     *
     * @param reservation
     * @param discountPercentage
     * @return apply discount for customer with more 3 reservations
     */
    public Double applyPromotion(Reservation reservation, double discountPercentage) throws SQLException {
        double reservationTotal = reservation.getTotalCost();
        double discountAmount = (discountPercentage / 100) * reservationTotal;
        double updatedTotalCost = reservationTotal - discountAmount;
        return updatedTotalCost;
    }


}
