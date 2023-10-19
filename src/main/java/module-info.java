module org.team.rentwheels {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.team.rentwheels to javafx.fxml;
    exports org.team.rentwheels;
    exports org.team.rentwheels.controllers;
    opens org.team.rentwheels.controllers to javafx.fxml;
}