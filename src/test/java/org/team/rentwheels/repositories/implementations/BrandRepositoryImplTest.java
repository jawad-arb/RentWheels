package org.team.rentwheels.repositories.implementations;

import org.junit.jupiter.api.Test;
import org.team.rentwheels.models.Brand;
import org.team.rentwheels.repositories.BrandRepository;

import java.sql.SQLException;
import java.util.prefs.BackingStoreException;

import static org.junit.jupiter.api.Assertions.*;

class BrandRepositoryImplTest {
    private final BrandRepository brandRepository=new BrandRepositoryImpl();
    @Test
    void getBrandByName() throws SQLException {
        Brand brand=new Brand();
        brand=brandRepository.getBrandByName("Honda");
        assertEquals(1,brand.getId());
    }

}