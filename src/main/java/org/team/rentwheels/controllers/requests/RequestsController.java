package org.team.rentwheels.controllers.requests;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.team.rentwheels.models.ReservationDTO;
import org.team.rentwheels.services.ReservationService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class RequestsController implements Initializable {
    @FXML
    private Pane acceptReservation;
    @FXML
    private Pane refuseReservation;

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

    public RequestsController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    public RequestsController() {
        this.reservationService = new ReservationService();
    }


    @FXML
    void acceptReservationEvent(MouseEvent event) {
        ReservationDTO selectedReservation=tableView.getSelectionModel().getSelectedItem();
        if (selectedReservation==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("You most select Reservation to Accepte it !");
            alert.setContentText(null);
            alert.showAndWait();
        }else {
            boolean confirmed=showConfirmationDialog(selectedReservation);
            if (confirmed){
                try{
                    reservationService.updateReservationStatus(selectedReservation.getId(),"Confirmed");
                    tableView.getItems().remove(selectedReservation);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void removeReservationEvent(MouseEvent event) {
        ReservationDTO selectedReservation=tableView.getSelectionModel().getSelectedItem();
        if (selectedReservation==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("You most select Reservation to refuse it !");
            alert.setContentText(null);
            alert.showAndWait();
        }else {
            boolean confirmed=showRefusingDialog(selectedReservation);
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

    public void loadReservationData() throws SQLException {
        List<ReservationDTO> reservationList=reservationService.getAllPendingReservation();
        tableView.getItems().addAll(reservationList);
    }

    private boolean showRefusingDialog(ReservationDTO reservationDTO) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Refusing Confirmation");
        alert.setContentText("Are you sure you want to refuse this Reservation ?");
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

    private boolean showConfirmationDialog(ReservationDTO reservationDTO) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Accepting Reservation");
        alert.setContentText("Are you sure you want to Accepte this Reservation ?");
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
}
