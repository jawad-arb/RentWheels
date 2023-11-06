package org.team.rentwheels.services;

import org.team.rentwheels.models.Car;
import org.team.rentwheels.repositories.CarRepository;
import org.team.rentwheels.repositories.implementations.CarRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    private final CarRepository carRepository;

    public CarService() {
        this.carRepository=new CarRepositoryImpl();
    }

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void addCar(Car addedCar) throws SQLException {
        this.carRepository.addCar(addedCar);
    }
    public void deleteCar(int carId) throws SQLException {
        this.carRepository.deleteCar(carId);
    }
    public void updateCar(int carId,Car updatedCar) throws SQLException {
        this.carRepository.updateCar(carId,updatedCar);
    }
    public Car getCarById(int carId) throws SQLException {
        return this.carRepository.getCarById(carId);
    }
    public List<Car> getAllCars() throws SQLException {
        return this.carRepository.getAllCars();
    }
}
