package org.team.rentwheels.services;

import org.junit.jupiter.api.Test;
import org.team.rentwheels.exceptions.BrandNotFoundException;
import org.team.rentwheels.models.Brand;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BrandServiceTest {
    private final BrandService brandService=new BrandService();
    @Test
    void getBrandByName() throws SQLException, BrandNotFoundException {
        Brand brand=new Brand();
        brand=brandService.getBrandByName("Honda");
        assertEquals(2008,brand.getFoundationYear());
            }
}