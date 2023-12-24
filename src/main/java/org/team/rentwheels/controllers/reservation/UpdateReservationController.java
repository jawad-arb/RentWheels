package org.team.rentwheels.controllers.reservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.team.rentwheels.exceptions.*;
import org.team.rentwheels.models.Car;
import org.team.rentwheels.models.Customer;
import org.team.rentwheels.models.Reservation;
import org.team.rentwheels.models.ReservationDTO;
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
import java.util.List;
import java.util.ResourceBundle;

public class UpdateReservationController implements Initializable {
    private ReservationService reservationService;
    private ReservationsController reservationsController;
    private ReservationDTO reservationDTO;

    @FXML
    private Button updateBtn;
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
    private final CarService carService ;
    private final CustomerService customerService ;
    private int customerId;
    private int carId;
    Date reservationDate ;
    Date startDate;
    Date endDate;
    double advPrice=0.0;

    public UpdateReservationController(ReservationService reservationService, ReservationsController reservationsController, CarService carService, CustomerService customerService) {
        this.reservationService = reservationService;
        this.reservationsController = reservationsController;
        this.carService = carService;
        this.customerService = customerService;
    }
    public UpdateReservationController() {
        this.carService = new CarService();
        this.customerService = new CustomerService();
        this.reservationsController = new ReservationsController();
        this.reservationService = new ReservationService();
    }
    public void initData(ReservationService reservationService, ReservationsController reservationsController, ReservationDTO selectedReservation) throws SQLException, CustomerNotExistsException {
        this.reservationService = reservationService;
        this.reservationsController = reservationsController;
        this.reservationDTO = selectedReservation;
        initializeData();
    }

    private void initializeData() throws SQLException, CustomerNotExistsException {
        reservationDTO=reservationsController.getSelectedReservation();
        if (reservationDTO != null) {
            int carId=reservationService.getCarIdByReservation(reservationDTO.getId());
            carModelCombo.getSelectionModel().select(carService.getCarById(carId));
            int customerId=reservationService.getCustomerByReservation(reservationDTO.getId());
            customerCombo.getSelectionModel().select(customerService.getCustomerById(customerId));
            reservationDatePicker.setValue(reservationDTO.getReservationDate().toLocalDate());
            startDatePicker.setValue(reservationDTO.getStartDate().toLocalDate());
            endDatePicker.setValue(reservationDTO.getEndDate().toLocalDate());
            advancedPriceTF.setText(String.valueOf(reservationDTO.getAdvancedPrice()));

        }
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

    @FXML
    void updateAction(ActionEvent event) {

        try {
            if (reservationDatePicker.getValue() == null || startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
                throw new NoFieldSelectedException("Please select all required dates.");
            }
            if (advancedPriceTF.getText().isEmpty() || carModelCombo.getSelectionModel().getSelectedItem() == null || customerCombo.getSelectionModel().getSelectedItem() == null ) {
                throw new NoFieldSelectedException("Please select all required fields.");
            }
            advPrice = Double.parseDouble(advancedPriceTF.getText());
            if(advPrice < 500){
                throw new AdvancedPricBandException("Please enter an advanced Price sup that 500 DH !");
            }
            LocalDate getReservationDate=reservationDatePicker.getValue();
            reservationDate=java.sql.Date.valueOf(getReservationDate);
            LocalDate getStartDate=startDatePicker.getValue();
            startDate=java.sql.Date.valueOf(getStartDate);
            LocalDate getEndDate=startDatePicker.getValue();
            endDate=java.sql.Date.valueOf(getEndDate);
            Customer selectedCustomer = customerCombo.getSelectionModel().getSelectedItem();
            if (selectedCustomer == null)
                throw new NoCustomerSelectedException("No Customer Selected");
            try {
                customerId=customerService.customerIdByName(selectedCustomer.getFirstName(),selectedCustomer.getLastName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            Car selectedCar=carModelCombo.getSelectionModel().getSelectedItem();
            if(selectedCar==null)
                throw new NoCarSelectedException("No Car Selected");
            try {
                carId=carService.carIdByModel(selectedCar.getModel());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Reservation reservation = new Reservation(new Car(carId), new Customer(customerId), reservationDate, startDate, endDate, calculateTotalCost(new Car(carId),startDate,endDate,advPrice), advPrice, "Confirmed");
            reservationService.updateReservation(reservationDTO.getId(),reservation);
            succesAlert();
            populateReservationTable();
            Stage stage = (Stage) updateBtn.getScene().getWindow();
            stage.close();
        }catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (NoFieldSelectedException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (AdvancedPricBandException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advenced Price");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ReservationNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoCustomerSelectedException e) {
            throw new RuntimeException(e);
        } catch (NoCarSelectedException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cancelAction(ActionEvent event) {

    }

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


    void succesAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reservation Success");
        alert.setContentText("Car reserved successfully!");
        alert.showAndWait();
    }


    public Double calculateTotalCost(Car car, Date startDate, Date endDate, double advancedPrice) throws SQLException {
        Double cost=carService.getCostByCarId(car.getCarId());
        int NbrDays=reservationService.calculateNumberOfDays(startDate,endDate);
        return (cost*NbrDays)-advancedPrice;
    }

    //
    private void populateReservationTable() throws SQLException {
        List<ReservationDTO> reservationDTOS = reservationService.getAllConfirmedReservation();
        reservationsController.tableView.setItems(FXCollections.observableList(reservationDTOS));
    }



}



