module org.team.rentwheels {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.team.rentwheels to javafx.fxml;
    exports org.team.rentwheels;
}