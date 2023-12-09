package org.team.rentwheels.controllers.reservation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.team.rentwheels.RentWheels;
import org.team.rentwheels.models.Reservation;
import org.team.rentwheels.models.ReservationDTO;
import org.team.rentwheels.services.ReservationService;

import java.io.IOException;
import java.net.URL;
import java.sql.ClientInfoStatus;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationsController implements Initializable {
    @FXML
    public TableColumn<ReservationDTO, String> clCarName;

    @FXML
    public TableColumn<ReservationDTO, String> clCustomerName;

    @FXML
    public TableColumn<ReservationDTO, Date> clDateReservation;

    @FXML
    public TableColumn<ReservationDTO, Date> clEndDate;

    @FXML
    public TableColumn<ReservationDTO, Date> clStartDate;

    @FXML
    public TextField searchBar;

    @FXML
    public TableView<ReservationDTO> tableView;
    private final ReservationService reservationService;

    public ReservationsController() {
        this.reservationService = new ReservationService();
    }
    public ReservationsController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clCarName.setCellValueFactory(new PropertyValueFactory<>("carName"));
        clCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        clDateReservation.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        clStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        clEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        try {
            loadReservationData();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addReservationEvent(MouseEvent event) {
        openAddBrandWindow();
    }

    @FXML
    void deleteReservationEvent(MouseEvent event) {

    }

    @FXML
    void updateReservationEvent(MouseEvent event) {

    }


    
    private void loadReservationData() throws SQLException {
        List<ReservationDTO> reservationList=reservationService.getAllConfirmedReservation();
        tableView.getItems().addAll(reservationList);
    }

    private void openAddBrandWindow() {
        FXMLLoader fxmlLoader=new FXMLLoader(RentWheels.class.getResource("fxml/Reservation/addReservation.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Stage addReservationStage = new Stage();
            Scene scene = new Scene(root);
            addReservationStage.setScene(scene);
            addReservationStage.setTitle("Add Reservation");
            addReservationStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
