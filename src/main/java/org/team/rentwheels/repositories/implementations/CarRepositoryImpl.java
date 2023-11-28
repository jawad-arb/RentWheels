package org.team.rentwheels.repositories.implementations;

import org.team.rentwheels.models.Car;
import org.team.rentwheels.repositories.CarRepository;
import org.team.rentwheels.utils.DatabaseOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.team.rentwheels.queries.CarQuery.*;

public class CarRepositoryImpl implements CarRepository {
    DatabaseOperations dbOperations=new DatabaseOperations();
    @Override
    public void addCar(Car addedCar) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(ADD_CAR_QUERY);
        ps.setInt(1,addedCar.getBrandId());
        ps.setString(2,addedCar.getModel());
        ps.setDouble(3,addedCar.getPrice());
        ps.setBoolean(4,addedCar.isAvailable());
        ps.setBytes(5,addedCar.getCar_image());
        ps.setString(6,addedCar.getMaintenance_status());
        ps.setDate(7,addedCar.getLast_maintenance_date());
        ps.setDate(8,addedCar.getCarInsuranceStartDate());
        ps.setDate(9,addedCar.getCarInsuranceEndDate());
        ps.executeUpdate();
    }

    @Override
    public void deleteCar(int carId) throws SQLException {
        PreparedStatement ps=dbOperations.setConnection(DELETE_CAR_BY_ID);
        ps.setInt(1,carId);
        ps.executeUpdate();
    }

    @Override
    public void updateCar(int carId, Car updatedCar) throws SQLException {
        PreparedStatement ps= dbOperations.setConnection(UPDATE_CAR_BY_ID);
        ps.setInt(1,updatedCar.getBrandId());
        ps.setString(2,updatedCar.getModel());
        ps.setDouble(3,updatedCar.getPrice());
        ps.setBoolean(4,updatedCar.isAvailable());
        ps.setBytes(5,updatedCar.getCar_image());
        ps.setString(6,updatedCar.getMaintenance_status());
        ps.setDate(7,updatedCar.getLast_maintenance_date());
        ps.setDate(8,updatedCar.getCarInsuranceStartDate());
        ps.setDate(9,updatedCar.getCarInsuranceEndDate());
        ps.setInt(10,carId);
        ps.executeUpdate();
        if (ps != null) {
            ps.close();
        }
    }

    @Override
    public Car getCarById(int carId) throws SQLException {
        PreparedStatement ps= dbOperations.setConnection(GET_CAR_BY_ID);
        ps.setInt(1,carId);
        ResultSet rs=ps.executeQuery();
        if (rs.next()){
            Car car=new Car();
            car.setCarId(rs.getInt("car_id"));
            car.setBrandId(rs.getInt("brand_id"));
            car.setModel(rs.getString("model"));
            car.setPrice(rs.getDouble("price"));
            car.setAvailable(rs.getBoolean("availability"));
            car.setMaintenance_status(rs.getString("maintenance_status"));
            car.setLast_maintenance_date(rs.getDate("last_maintenance_date"));
            car.setCarInsuranceStartDate(rs.getDate("Insurance_start_date"));
            car.setCarInsuranceEndDate(rs.getDate("Insurance_end_date"));
            return car;
        }
        return null;
    }

    @Override
    public List<Car> getAllCars() throws SQLException {
        List<Car> carsList=new ArrayList<>();
        PreparedStatement ps= dbOperations.setConnection(GET_ALL_CARS);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            Car car=new Car();
            car.setCarId(rs.getInt("car_id"));
            car.setBrandId(rs.getInt("brand_id"));
            car.setModel(rs.getString("model"));
            car.setPrice(rs.getDouble("price"));
            car.setAvailable(rs.getBoolean("availability"));
            car.setMaintenance_status(rs.getString("maintenance_status"));
            car.setLast_maintenance_date(rs.getDate("last_maintenance_date"));
            car.setCarInsuranceStartDate(rs.getDate("Insurance_start_date"));
            car.setCarInsuranceEndDate(rs.getDate("Insurance_end_date"));
            carsList.add(car);
        }
        return carsList;
    }

}
