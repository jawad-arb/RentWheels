package org.team.rentwheels.repositories.implementations;

import org.team.rentwheels.DatabaseConnection;
import org.team.rentwheels.models.Brand;
import org.team.rentwheels.repositories.BrandRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.team.rentwheels.queries.BrandQuery.*;

public class BrandRepositoryImpl implements BrandRepository {



    @Override
    public void addBrand(String brandName, String countyOfMake, int foundationYear, byte[] imageByte) throws SQLException, IOException {
        PreparedStatement ps = setConnection(ADD_BRAND_QUERY);
        ps.setString(1, brandName);
        ps.setString(2, countyOfMake);
        ps.setInt(3, foundationYear);
        ps.setBytes(4, imageByte);
        ps.executeUpdate();
    }

    @Override
    public List<Brand> getBrandByName(String brandName) throws SQLException {
        List<Brand> brandList = new ArrayList<>();
        PreparedStatement ps = setConnection(GET_BRAND_BY_NAME);
        ps.setString(1, brandName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Brand brand = new Brand();
            brand.setId(rs.getInt("brand_id"));
            brand.setBrandName(rs.getString("name"));
            brand.setCountyOfMake(rs.getString("country"));
            brand.setImage(rs.getBytes("image_data"));
            brandList.add(brand);
        }
        return brandList;
    }


    @Override
    public void DeleteBrandById(int id) throws SQLException {
        PreparedStatement ps =setConnection(DELETE_BRAND_BY_ID);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    public void updateBrand(int brandId, Brand updatedBrand) throws SQLException {
        PreparedStatement ps=setConnection(UPDATE_BRAND_WHERE_ID);
        ps.setString(1,updatedBrand.getBrandName());
        ps.setString(2,updatedBrand.getCountyOfMake());
        ps.setInt(3,updatedBrand.getFoundationYear());
        ps.setInt(4,brandId);
        ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
    }


    // prepare the statement
    public PreparedStatement setConnection(String query) throws SQLException {
        Connection connection=DatabaseConnection.getConnection();
        return connection.prepareStatement(query);
    }





//    public static void main(String[] args) throws SQLException {
//        BrandRepository brandRepository=new BrandRepositoryImpl();
//        List<Brand> brandList = brandRepository.getBrandByName("Honda");
//        if (brandList.isEmpty()) {
//            System.out.println("No brands found with the name: " );
//        } else {
//            System.out.println("Brands with the name :");
//            for (Brand brand : brandList) {
//                System.out.println("Brand ID: " + brand.getId());
//                System.out.println("Brand Name: " + brand.getBrandName());
//                System.out.println("Country of Make: " + brand.getCountyOfMake());
//                // Print other attributes as needed
//            }
//        }

//    public static void main(String[] args) throws SQLException {
//        BrandRepository brandRepository=new BrandRepositoryImpl();
//        Brand updatedBrand=new Brand("dacia","Maroc",2020);
//        brandRepository.updateBrand(25,updatedBrand);
//    }

//

//    }



}