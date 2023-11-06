package org.team.rentwheels.queries;

public class ReservationQuery {
    public static final String GET_RESERVATION_BY_ID = "SELECT * FROM Reservations WHERE reservation_id=?";
    public static final String GET_RESERVATIONS_BY_CAR_ID = "SELECT * FROM Reservations WHERE car_id=?";
    public static final String GET_RESERVATIONS_BY_CUSTOMER_ID = "SELECT * FROM Reservations WHERE customer_id=?";
    public static final String GET_COUNT_RESERVATION_BY_CUSTOMER_ID="SELECT COUNT(*) FROM Reservations WHERE customer_id=?";


}
