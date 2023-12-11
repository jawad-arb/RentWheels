package org.team.rentwheels.queries;

public class ReservationQuery {
    public static final String GET_RESERVATION_BY_ID = "SELECT * FROM Reservations WHERE reservation_id=?";
    public static final String GET_RESERVATIONS_BY_CAR_ID = "SELECT * FROM Reservations WHERE car_id=?";
    public static final String GET_RESERVATIONS_BY_CUSTOMER_ID = "SELECT * FROM Reservations WHERE customer_id=?";
    public static final String GET_COUNT_RESERVATION_BY_CUSTOMER_ID= "SELECT COUNT(*) FROM Reservations WHERE customer_id=?";
    public static final String GET_TOTAL_NUMBER_OF_RESERVATIONS = "SELECT COUNT(*) FROM Reservations";
    public static final String CALCULATE_TOTAL_REVENUE_FROM_RESERVATIONS = "SELECT SUM(total_cost) FROM Reservations;";
    public static final String ADD_RESERVATION_QUEY = "insert into Reservations(car_id,customer_id,reservation_date,start_date,end_date,total_cost,status,advanced_price)VALUES (?,?,?,?,?,?,?,?)";
    public static final String DELETE_RESERVATION_BY_ID="DELETE FROM Reservations WHERE reservation_id=?";
    public static final String UPDATE_RESERVATION="UPDATE Reservations SET car_id=?,customer_id=?,reservation_date=?,start_date=?,end_date=?,total_cost=?,status=?,advanced_price=? WHERE reservation_id=?";
    public static final String GET_ALL_RESERVATION_FOR_TABLE_VIEW = "SELECT c.model, CONCAT(cm.first_name, ' ', cm.last_name) AS customer_name, r.reservation_date, r.start_date, r.end_date FROM Reservations r INNER JOIN Cars c ON c.car_id = r.car_id INNER JOIN Customers cm ON r.customer_id = cm.customer_id WHERE r.status = ?";

}
