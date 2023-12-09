package org.team.rentwheels.repositories.implementations;

import org.team.rentwheels.models.Brand;
import org.team.rentwheels.repositories.BrandRepository;
import org.team.rentwheels.utils.DatabaseOperations;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.team.rentwheels.queries.BrandQuery.*;

public class BrandRepositoryImpl implements BrandRepository {
    DatabaseOperations dbOperations = new DatabaseOperations();



    @Override
    public void addBrand(String brandName, String countyOfMake, int foundationYear, byte[] imageByte) throws SQLException, IOException {
        PreparedStatement ps = dbOperations.setConnection(ADD_BRAND_QUERY);
        ps.setString(1, brandName);
        ps.setString(2, countyOfMake);
        ps.setInt(3, foundationYear);
        ps.setBytes(4, imageByte);
        ps.executeUpdate();
    }

    @Override
    public Brand getBrandByName(String brandName) throws SQLException {
        PreparedStatement ps = dbOperations.setConnection(GET_BRAND_BY_NAME);
        ps.setString(1, brandName);
        ResultSet rs = ps.executeQuery();
        if (!rs.next())
            return null;
        Brand brand = new Brand();
        brand.setId(rs.getInt("brand_id"));
        brand.setBrandName(rs.getString("name"));
        brand.setCountyOfMake(rs.getString("country"));
        brand.setFoundationYear(rs.getInt("foundation_year"));
        brand.setImage(rs.getBytes("image_data"));
        return brand;
    }

    @Override
    public List<Brand> getAllBrands() throws SQLException {
        List<Brand> brandList = new ArrayList<>();
        PreparedStatement ps = dbOperations.setConnection(GET_ALL_BRANDS);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Brand brand = new Brand();
            brand.setId(rs.getInt("brand_id"));
            brand.setBrandName(rs.getString("name"));
            brand.setCountyOfMake(rs.getString("country"));
            brand.setFoundationYear(rs.getInt("foundation_year"));
            brand.setImage(rs.getBytes("image_data"));
            brandList.add(brand);
        }

        return brandList;
    }

    @Override
    public void DeleteBrandById(int id) throws SQLException {
        PreparedStatement ps =dbOperations.setConnection(DELETE_BRAND_BY_ID);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    public void updateBrand(int brandId, Brand updatedBrand) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(UPDATE_BRAND_WHERE_ID);
        ps.setString(1, updatedBrand.getBrandName());
        ps.setString(2, updatedBrand.getCountyOfMake());
        ps.setInt(3, updatedBrand.getFoundationYear());
        ps.setBytes(4, updatedBrand.getImage());
        ps.setInt(5, brandId);
        ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
    }

    @Override
    public boolean isBrandExists(String name) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(CHECK_IF_BRAND_EXISTS);
        ps.setString(1,name);
        ResultSet rs=ps.executeQuery();
        if(!rs.next())
            return false;
        int result=rs.getInt(1);
        return result>0;
    }


}