package org.team.rentwheels.queries;

public class CustomerQuery {
    public static final String ADD_CUSTOMER_QUERY = "INSERT INTO Customers(first_name,last_name,email,phone,address)Values(?,?,?,?,?)";
    public static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM Customers WHERE customer_id=?";

    public static final String UPDATE_CUSTOMER_WHERE_ID = "UPDATE Customers SET first_name=? , last_name=? , email=? , phone =? , address=? WHERE customer_id=?";
    public static final String GET_CUSTOMER_BY_ID = "SELECT * FROM Customers WHERE customer_id=?";
    public static final String GET_ALL_CUSTOMERS = "SELECT  * FROM Customers";
    public static final String DOES_CUSTOMER_EXISTS = "SELECT COUNT(*) FROM Customers WHERE email=? ";
    public static final String CUSTOMER_EXISTS_BY_ID = "SELECT COUNT(*) FROM Customers WHERE customer_id=?";
    public static final String CUSTOMER_ID_BY_NAME = "SELECT customer_id FROM Customers WHERE first_name=? and last_name=?";
}
