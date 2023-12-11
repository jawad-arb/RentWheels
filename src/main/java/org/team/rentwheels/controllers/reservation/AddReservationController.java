package org.team.rentwheels.controllers.reservation;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.team.rentwheels.exceptions.*;
import org.team.rentwheels.models.Car;
import org.team.rentwheels.models.Customer;
import org.team.rentwheels.models.Reservation;
import org.team.rentwheels.services.CarService;
import org.team.rentwheels.services.CustomerService;
import org.team.rentwheels.services.ReservationService;
import org.team.rentwheels.utils.CarNameConverter;
import org.team.rentwheels.utils.CustomerNameConverter;
import org.team.rentwheels.utils.ReservationStartDateValidator;
import org.team.rentwheels.utils.StartEndDateValidator;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddReservationController implements Initializable {
    private final ReservationService reservationService;
    private final CarService carService ;
    private final CustomerService customerService ;
    private int customerId;
    private int carId;

    @FXML
    private TextField advancedPriceTF;
    @FXML
    private ComboBox<Car> carModelCombo;
    @FXML
    private ComboBox<Customer> customerCombo;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private DatePicker reservationDatePicker;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Text totalCostField;

    public AddReservationController(ReservationService reservationService, CarService carService, CustomerService customerService) throws SQLException {
        this.reservationService = reservationService;
        this.carService = carService;
        this.customerService = customerService;
    }
    public AddReservationController() throws SQLException {
        this.reservationService = new ReservationService();
        this.customerService = new CustomerService();
        this.carService = new CarService();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList allCars=carService.getAllAvailableCars();
            ObservableList allCustomers=customerService.getAllAvailableCustomers();
            carModelCombo.setItems(allCars);
            carModelCombo.setConverter(new CarNameConverter());
            customerCombo.setItems(allCustomers);
            customerCombo.setConverter(new CustomerNameConverter());
            StartEndDateValidator validator = new StartEndDateValidator(startDatePicker, endDatePicker);
            ReservationStartDateValidator validator1 = new ReservationStartDateValidator(reservationDatePicker, startDatePicker);
            startDatePicker.valueProperty().addListener(validator);
            startDatePicker.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);

                    // Disable previous dates
                    setDisable(empty || date.isBefore(LocalDate.now()));
                }
            });
            endDatePicker.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);

                    setDisable(empty || date.isBefore(startDate.toLocalDate()));
                }
            });
            reservationDatePicker.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.isBefore(LocalDate.now()));
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Date reservationDate ;
    Date startDate;
    Date endDate;

    /**
     * @Info Get the CustomerId
     * @param event
     * @throws NoCustomerSelectedException
     */
    @FXML
    void customerAction(ActionEvent event) throws NoCustomerSelectedException {
        Customer selectedCustomer = customerCombo.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null)
            throw new NoCustomerSelectedException("No Customer Selected");
        try {
            customerId=customerService.customerIdByName(selectedCustomer.getFirstName(),selectedCustomer.getLastName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Info Get the Car Id
     * @param event
     * @throws NoCarSelectedException
     */
    @FXML
    void carAction(ActionEvent event) throws NoCarSelectedException {
        Car selectedCar=carModelCombo.getSelectionModel().getSelectedItem();
        if(selectedCar==null)
            throw new NoCarSelectedException("No Car Selected");
        try {
            carId=carService.carIdByModel(selectedCar.getModel());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void reservationDateEvent(ActionEvent event) {
        LocalDate getReservationDate=reservationDatePicker.getValue();
        reservationDate = java.sql.Date.valueOf(getReservationDate);
    }

    @FXML
    void reservationEndDateEvent(ActionEvent event) {
        LocalDate getReservationEndDate=endDatePicker.getValue();
        endDate = java.sql.Date.valueOf(getReservationEndDate);
    }

    @FXML
    void reservationStartDateEvent(ActionEvent event) {
        LocalDate getReservationStartDate=startDatePicker.getValue();
        startDate = java.sql.Date.valueOf(getReservationStartDate);
    }


    @FXML
    void saveAction(ActionEvent event) throws CarNotAvailableException, SQLException, CustomerInBlackListException, NoCarSelectedException, NoFieldSelectedException {
        try {
            if (reservationDatePicker.getValue() == null || startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
                throw new NoFieldSelectedException("Please select all required dates.");
            }
            if (advancedPriceTF.getText().isEmpty() || carModelCombo.getSelectionModel().getSelectedItem() == null || customerCombo.getSelectionModel().getSelectedItem() == null ) {
                throw new NoFieldSelectedException("Please select all required fields.");
            }
            double advPrice = Double.parseDouble(advancedPriceTF.getText());
            Reservation reservation = new Reservation(new Car(carId), new Customer(customerId), reservationDate, startDate, endDate, 100.0, advPrice, "Confirmed");
            reservationService.addReservation(reservation);
            succesAlert();
            }catch (NumberFormatException e) {
                e.printStackTrace();
            }catch (CustomerInBlackListException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Customer Blacklisted");
                alert.setHeaderText("The customer cannot be added to a reservation.");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }catch (CarNotAvailableException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Reservation Error");
                alert.setContentText("The car is not available for the selected dates.");
                alert.showAndWait();
            } catch (NoFieldSelectedException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Information");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
    }



    void succesAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reservation Success");
        alert.setContentText("Car reserved successfully!");
        alert.showAndWait();
    }
    void errorAlert(String title,String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }



}
