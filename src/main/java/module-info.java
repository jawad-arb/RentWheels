module org.team.rentwheels {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.team.rentwheels to javafx.fxml;

    opens org.team.rentwheels.models to javafx.base;


    exports org.team.rentwheels;

    exports org.team.rentwheels.controllers.Login;

}