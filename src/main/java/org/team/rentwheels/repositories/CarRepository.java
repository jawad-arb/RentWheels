package org.team.rentwheels.repositories;

import javafx.collections.ObservableList;
import org.team.rentwheels.models.Car;
import java.sql.SQLException;
import java.util.List;

public interface CarRepository {
    void addCar(Car addedCar) throws SQLException;
    void deleteCar(int carId) throws SQLException;
    void updateCar(int carId,Car updatedCar) throws SQLException;
    Car getCarById(int carId) throws SQLException;
    List<Car> getAllCars() throws SQLException;
    public ObservableList getAllAvailableCars() throws SQLException ;
    int carIdByModel(String model) throws SQLException;

}
