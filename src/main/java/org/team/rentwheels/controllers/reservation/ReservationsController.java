package org.team.rentwheels.controllers.reservation;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.team.rentwheels.RentWheels;
import org.team.rentwheels.exceptions.CustomerNotExistsException;
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
    public TableView<ReservationDTO> getTableView() {
        return tableView;
    }

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
    void addReservationEvent(MouseEvent event) throws SQLException {
        openAddBrandWindow();
    }

    @FXML
    void deleteReservationEvent(MouseEvent event) {
        ReservationDTO selectedReservation=tableView.getSelectionModel().getSelectedItem();
        if (selectedReservation==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("You most select Reservation to Deleted !");
            alert.setContentText(null);
            alert.showAndWait();
        }else {
            boolean confirmed=showConfirmationDialog(selectedReservation);
            if (confirmed){
                try{
                    reservationService.deleteReservation(selectedReservation.getId());
                    tableView.getItems().remove(selectedReservation);
                }catch (Exception e) {
                    e.printStackTrace();
                }
        }
        }
    }

    @FXML
    void updateReservationEvent(MouseEvent event) throws SQLException {
        openUpdateReservationWindow();
    }


    
    public void loadReservationData() throws SQLException {
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
    public void openUpdateReservationWindow(){
        ReservationDTO reservationDTO=getSelectedReservation();
        if (reservationDTO==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("You most select Reservation to Updated !");
            alert.setContentText(null);
            alert.showAndWait();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(RentWheels.class.getResource("fxml/Reservation/updateReservation.fxml"));
            try {
                Parent root = fxmlLoader.load();
                UpdateReservationController updateReservationController = fxmlLoader.getController();
                updateReservationController.initData(reservationService, this, reservationDTO);

                Stage updateBrandStage = new Stage();
                Scene scene = new Scene(root);
                updateBrandStage.setScene(scene);
                updateBrandStage.setTitle("Update Reservations");
                updateBrandStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (CustomerNotExistsException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean showConfirmationDialog(ReservationDTO reservationDTO) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Deleting Reservaation : ");
        alert.setContentText("Are you sure you want to delete this Reservation ?");
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        boolean[] result = {false};
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                result[0] = true;
            } else {
                result[0] = false;
            }
        });

        return result[0];
    }

    public ReservationDTO getSelectedReservation() {
        if (tableView != null && tableView.getSelectionModel().getSelectedItem() != null) {
            return tableView.getSelectionModel().getSelectedItem();
        } else {
            return null;
        }
    }
}
