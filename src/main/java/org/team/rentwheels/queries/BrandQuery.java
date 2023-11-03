package org.team.rentwheels.queries;

public class BrandQuery {

    public static final String ADD_BRAND_QUERY="INSERT INTO Brands (name, country, foundation_year , image_data) VALUES (?,?,?,?)";
    public static final String GET_BRAND_BY_NAME = "SELECT * FROM Brands WHERE name = ? ";
    public static final String DELETE_BRAND_BY_ID = "DELETE FROM Brands WHERE brand_id = ? ";
    public static final String GET_ALL_BRANDS="SELECT * FROM Brands";

    public static final String UPDATE_BRAND_WHERE_ID="UPDATE Brands SET name= ? ,country= ?, foundation_year= ? , image_data = ? WHERE brand_id= ? ";


}
