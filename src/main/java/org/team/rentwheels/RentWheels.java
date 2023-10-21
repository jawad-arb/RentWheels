package org.team.rentwheels;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.team.rentwheels.utils.StageManager;

import java.io.IOException;

public class RentWheels extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StageManager.init(stage,true);
    }

    public static void main(String[] args) {
        launch();
    }
}