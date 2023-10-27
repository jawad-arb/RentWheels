package org.team.rentwheels.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.team.rentwheels.RentWheels;

import java.io.IOException;

public class StageManager {

    private  static Stage mainStage;
    private static Scene scene;
    private static int WIDTH = 700, HEIGHT = 450;
    private static int MAIN_WIDTH=1200,MAIN_HIGHT=700;


    public static void init(Stage stage, boolean resizable) throws IOException {
        mainStage = stage;
        Parent loader = FXMLLoader.load(RentWheels.class.getResource("fxml/Login/login.fxml"));
        scene = new Scene(loader,WIDTH,HEIGHT);
        mainStage.setResizable(resizable);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void replace(String file, boolean resizable) throws IOException,RuntimeException {
        Parent loader = FXMLLoader.load(RentWheels.class.getResource(file));
        mainStage.close();
//        scene = new Scene(loader,MAIN_WIDTH,MAIN_HIGHT);
        scene = new Scene(loader,600,400);
        mainStage.setScene(scene);
        mainStage.setResizable(resizable);
        mainStage.show();
    }


}
