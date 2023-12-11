package org.team.rentwheels.utils;

import java.time.LocalDate; // Use LocalDate instead of java.sql.Date
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

public class StartEndDateValidator implements ChangeListener<LocalDate> {

    private final DatePicker startDateField;
    private final DatePicker endDateField;

    public StartEndDateValidator(DatePicker startDateField, DatePicker endDateField) {
        this.startDateField = startDateField;
        this.endDateField = endDateField;

        // Listen for changes in selected date for both date pickers
        startDateField.valueProperty().addListener(this);
        endDateField.valueProperty().addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

        // Get the selected dates
        LocalDate startDate = startDateField.getValue();
        LocalDate endDate = endDateField.getValue();

        // Check if the start date is after the end date
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            // Display an error message to the user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Start and End Dates");
            alert.setHeaderText("The start date must be before the end date.");
            alert.setContentText("Please select valid start and end dates.");
            alert.showAndWait();
        }
    }
}
