package org.team.rentwheels.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class ReservationStartDateValidator implements ChangeListener<LocalDate> {
    private final DatePicker reservationDateFeild;
    private final DatePicker startDateField;


    public ReservationStartDateValidator(DatePicker reservationDateFeild, DatePicker startDateField) {
        this.startDateField = startDateField;
        this.reservationDateFeild = reservationDateFeild;

        // Listen for changes in selected date for both date pickers
        startDateField.valueProperty().addListener(this);
        reservationDateFeild.valueProperty().addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

        // Get the selected dates
        LocalDate startDate = startDateField.getValue();
        LocalDate reservationDate = reservationDateFeild.getValue();

        // Check if the start date is after the end date
        if (startDate != null && reservationDate != null && reservationDate.isAfter(startDate)) {
            // Display an error message to the user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Reservation and Start Dates");
            alert.setHeaderText("The Reservation date must be before the start date.");
            alert.setContentText("Please select valid start and end dates.");
            alert.showAndWait();
        }
    }
}
