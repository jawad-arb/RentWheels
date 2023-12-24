package org.team.rentwheels.repositories.implementations;

import org.junit.jupiter.api.Test;
import org.team.rentwheels.models.Car;
import org.team.rentwheels.repositories.CarRepository;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class
CarRepositoryImplTest {
    private CarRepository carRepository=new CarRepositoryImpl();

    @Test
    void addCar() throws SQLException {
        // given
        Car car = new Car( 20, "Toyota Camry 12", 100.0, true, null, "Good", Date.valueOf("2023-11-27"),Date.valueOf("2023-02-12"),Date.valueOf("2024-02-12"));
        //when
        carRepository.addCar(car);
    }

    @Test
    void deleteCar() throws SQLException {
        carRepository.deleteCar(9);
    }

    @Test
    void updateCar() throws SQLException {
        Car car = new Car( 20, "Toyota Camry 12", 100.0, true, null, "Good", Date.valueOf("2023-11-27"),Date.valueOf("2023-02-12"),Date.valueOf("2024-02-12"));
        carRepository.updateCar(2,car);
    }

    @Test
    void getCarById() throws SQLException {
        carRepository.getCarById(4);
    }

    @Test
    void getAllCars() {
    }

    @Test
    void getCostByCarId() throws SQLException {
        assertEquals(100,carRepository.getCostByCarId(2));
    }
}