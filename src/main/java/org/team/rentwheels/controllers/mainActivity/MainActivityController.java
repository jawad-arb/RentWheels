package org.team.rentwheels.controllers.mainActivity;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import org.team.rentwheels.controllers.login.LoginController;
import org.team.rentwheels.models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MainActivityController implements Initializable {
    @FXML
    public Text usernametxt;

    private User currentUser;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentUser = LoginController.getCurrentUser();
        usernametxt.setText(currentUser.getUserName());
    }
}
