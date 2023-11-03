package org.team.rentwheels.controllers.mainActivity;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.team.rentwheels.controllers.login.LoginController;
import org.team.rentwheels.models.User;
import org.team.rentwheels.utils.StageManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainActivityController implements Initializable {
    private static final int width=900,height=627;

    private User currentUser;

    @FXML
    public HBox BrandsHB;

    @FXML
    public ImageView logoutIV;

    @FXML
    public TextField searchTF;

    @FXML
    public Text usernametxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentUser = LoginController.getCurrentUser();
        usernametxt.setText(currentUser.getUserName());
    }




    @FXML
    void logoutEvent(MouseEvent event) throws IOException {
        StageManager.replace("fxml/Login/login.fxml",false,700,450);
    }

    @FXML
    void BrandEvent(MouseEvent event) throws IOException {
        StageManager.replace("fxml/Brand/brands.fxml",true,width,height);
    }

}
