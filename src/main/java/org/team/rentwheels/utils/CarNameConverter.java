package org.team.rentwheels.utils;

import javafx.util.StringConverter;
import org.team.rentwheels.models.Car;

public class CarNameConverter extends StringConverter<Car> {

    @Override
    public String toString(Car car) {
        return car.getModel();
    }

    @Override
    public Car fromString(String string) {
        // Not used in this case
        return null;
    }
}
