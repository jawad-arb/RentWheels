package org.team.rentwheels.queries;

public class BlackListQuery {
    public static final String ADD_CUSTOMER_TO_BLACK_LIST = "insert into Blacklist(customer_id, car_id, start_date, end_date, reason) VALUES (?,?,?,?,?)";
    public static final String DELETE_FROM_BLACK_LIST= "DELETE FROM Blacklist where entry_id=?";
    public static final String UPDATE_FROM_BLACK_LIST_BY_ENTRY_ID= "UPDATE Blacklist set customer_id=?,car_id=?,start_date=?,end_date=?,reason=? WHERE entry_id=?";
    public static final String GET_NUMBER_OF_CUSTOMERS_IN_BLACK_LIST= "SELECT COUNT(*) FROM Blacklist";
    public static final String GET_ITEM_FROM_BLACK_LIST_BY_ENTRY_ID= "SELECT (customer_id, car_id, start_date, end_date, reason) FROM Blacklist WHERE entry_id=?";
    public static final String CHECK_IF_CUSTOMER_EXISTS_IN_BLACK_LIST= "SELECT count(*) from Blacklist where customer_id=?";
    public static final String GET_ALL_ITEMS_IN_BLACK_LIST= "SELECT * FROM Blacklist";

}
