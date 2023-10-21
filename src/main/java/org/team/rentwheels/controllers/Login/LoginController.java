package org.team.rentwheels.controllers.Login;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.team.rentwheels.DatabaseConnection;
import org.team.rentwheels.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    @FXML
    public Button cancelBtn;
    @FXML
    public Label loginMessageLabel;
    @FXML
    public TextField userNameTextField;
    @FXML
    public PasswordField passwordPasswordField;


    public void loginButtonOnAction(ActionEvent e) throws SQLException {
        if (!userNameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            validateLogin();
        }else {
            loginMessageLabel.setText("Please enter a username and password");
        }
    }



    /**
     * @AllMethods
     * */

    private void validateLogin() throws SQLException {
        //connect to database
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.connection();
        String VERIFIE_LOGIN="SELECT COUNT(1) FROM Users WHERE username='"+userNameTextField.getText()+"' AND password ='"+passwordPasswordField.getText()+"'";
        // execute the statement
        Statement statement=connectDB.createStatement();
        ResultSet queryResult=statement.executeQuery(VERIFIE_LOGIN);

        //verifie the results
        while(queryResult.next()){
            if (queryResult.getInt(1)==1){
                User user=new User();
                loginMessageLabel.setText("Welcome to the application !!");
            }else {
                loginMessageLabel.setText("Invalid Login , Please Try Again");
            }
        }
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage =(Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }


}
