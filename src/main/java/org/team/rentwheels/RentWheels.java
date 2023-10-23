package org.team.rentwheels;

import javafx.application.Application;
import javafx.stage.Stage;
import org.team.rentwheels.utils.StageManager;

import java.io.IOException;

public class RentWheels extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StageManager.init(stage,true);
//        System.out.println(LoginController.getCurrentUser());

    }

    public static void main(String[] args) {
        launch();

    }
}