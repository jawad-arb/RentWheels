package org.team.rentwheels.services;

import org.team.rentwheels.models.Brand;
import org.team.rentwheels.repositories.BrandRepository;
import org.team.rentwheels.repositories.implementations.BrandRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(){
        this.brandRepository=new BrandRepositoryImpl();
    }
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void addBrand(String brandName,
                         String countyOfMake,
                         int foundationYear,
                         byte[] file) throws SQLException, IOException {
        this.brandRepository.addBrand(brandName,countyOfMake,foundationYear,file);
    }
    public List<Brand> getAllBrands() throws SQLException {
        return brandRepository.getAllBrands();
    }

    public void updateBrand(int id , Brand updatedBrand) throws SQLException {
        this.brandRepository.updateBrand(id,updatedBrand);
    }

    public void deleteBrand(int id) throws SQLException {
        this.brandRepository.DeleteBrandById(id);
    }


}
