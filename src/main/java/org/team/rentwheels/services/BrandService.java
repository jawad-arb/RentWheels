package org.team.rentwheels.services;

import org.team.rentwheels.repositories.BrandRepository;
import org.team.rentwheels.repositories.implementations.BrandRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;

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

}
