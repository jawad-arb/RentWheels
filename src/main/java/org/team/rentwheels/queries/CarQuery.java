package org.team.rentwheels.queries;

public class CarQuery {

    public static final  String ADD_CAR_QUERY = "INSERT INTO Cars(brand_id,model,price,availability,car_image,maintenance_status,last_maintenance_date,Insurance_start_date,Insurance_end_date) VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String DELETE_CAR_BY_ID = "DELETE FROM Cars WHERE car_id=?";
    public static final String GET_CAR_BY_ID = "SELECT * FROM Cars WHERE car_id=?";
    public static final String GET_ALL_AVAILABLE_CARS = "SELECT * FROM Cars WHERE availability=1";
    public static String GET_ALL_CARS="SELECT * FROM Cars";
    public static String UPDATE_CAR_BY_ID="UPDATE Cars SET brand_id=? ,model=?,price=?,availability=?,car_image=?,maintenance_status=?,last_maintenance_date=?,Insurance_start_date=?,Insurance_end_date=? WHERE car_id=?";
    public static final String GET_CAR_ID_BY_MODEL = "SELECT car_id FROM Cars WHERE model=?";


}
