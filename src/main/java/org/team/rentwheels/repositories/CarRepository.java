package org.team.rentwheels.repositories;

import org.team.rentwheels.models.Car;
import java.sql.SQLException;
import java.util.List;

public interface CarRepository {
    void addCar(Car addedCar) throws SQLException;
    void deleteCar(int carId) throws SQLException;
    void updateCar(int carId,Car updatedCar) throws SQLException;
    Car getCarById(int carId) throws SQLException;
    List<Car> getAllCars() throws SQLException;

//    int carId,
//    int brandId,
//    String model,
//    double price,
//    boolean isAvailable,
//    byte[] car_image,
//    String maintenance_status,
//    Date last_maintenance_date
}
