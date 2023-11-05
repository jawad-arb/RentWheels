package org.team.rentwheels.repositories;

import org.team.rentwheels.models.Brand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BrandRepository {
    void addBrand(String brandName, String countyOfMake, int foundationYear, byte[] imageByte) throws SQLException, IOException;
    List<Brand> getBrandByName(String brandName) throws SQLException;
    List<Brand> getAllBrands() throws SQLException;
    void DeleteBrandById(int id) throws SQLException;
    void updateBrand(int id,Brand updatedBrand) throws SQLException;

}
