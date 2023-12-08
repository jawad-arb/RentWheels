package org.team.rentwheels.controllers.mainActivity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.team.rentwheels.RentWheels;
import org.team.rentwheels.controllers.login.LoginController;
import org.team.rentwheels.models.User;
import org.team.rentwheels.utils.StageManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainActivityController implements Initializable {
    private String fxmlURL = "fxml/";
    private static final int width=900,height=627;
    private User currentUser;
    @FXML
    public Text usernametxt;
    @FXML
    private StackPane container;

    @FXML
    void dashPaneAction(MouseEvent event) {
        try{
            load("Dashboard/dashboard.fxml");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void blackListAct(ActionEvent event) {
        try{
            load("BlackList/BlackList.fxml");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void bookingAct(ActionEvent event) {
        try{
            load("Booking/");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void brandAct(ActionEvent event) {
        try{
            load("Brand/brands.fxml");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void carAct(ActionEvent event) {
        try{
            load("Cars/cars.fxml");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void customerAct(ActionEvent event) {
        try{
            load("Customers/customers.fxml");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void logOutAct(ActionEvent event) {
        try{
            StageManager.replace("fxml/Login/login.fxml",true,960,530);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void statAct(ActionEvent event) {
        try{
            load("Statistics/stat.fxml");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void dashBoardAct(ActionEvent event) {
        try{
            load("Dashboard/dashboard.fxml");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentUser = LoginController.getCurrentUser();
        usernametxt.setText(currentUser.getFirstName());

        try{
            load("Dashboard/dashboard.fxml");

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void logoutEvent(MouseEvent event) throws IOException {
        StageManager.replace("fxml/Login/login.fxml",false,700,450);
    }

    @FXML
    void BrandEvent(MouseEvent event) throws IOException {
        StageManager.replace("fxml/Brand/brands.fxml",true,width,height);
    }

    private void load(String file) throws IOException {
        Parent fxml = FXMLLoader.load(RentWheels.class.getResource(fxmlURL + file));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

}
