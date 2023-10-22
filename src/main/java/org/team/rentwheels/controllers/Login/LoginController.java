package org.team.rentwheels.controllers.Login;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.team.rentwheels.models.User;
import org.team.rentwheels.services.UserService;

import java.sql.SQLException;

public class LoginController {

    // dependency injection
    private final UserService userService;
    private static User currentUser;
    @FXML
    public Button cancelBtn;
    @FXML
    public Label loginMessageLabel;
    @FXML
    public TextField userNameTextField;
    @FXML
    public PasswordField passwordPasswordField;

    public LoginController(){
        this.userService=new UserService();
    }

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    public void loginButtonOnAction(ActionEvent e) throws SQLException {
        if (!userNameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            User user = new User();
            user = userService.getUserIfExist(userNameTextField.getText(),passwordPasswordField.getText());
            if (user != null){
                currentUser = user;
                loginMessageLabel.setText("Welcome to the application !!");
            }else {
                loginMessageLabel.setText("Invalid Login , Please Try Again !!");
            }
        }else {
            loginMessageLabel.setText("Please enter a username and password");
        }
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage =(Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }




}
