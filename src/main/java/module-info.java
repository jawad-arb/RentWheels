module org.team.rentwheels {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.team.rentwheels to javafx.fxml;
    opens org.team.rentwheels.controllers.brand to javafx.fxml;
    opens org.team.rentwheels.controllers.mainActivity to javafx.fxml;
    opens org.team.rentwheels.models to javafx.base;


    exports org.team.rentwheels;

    exports org.team.rentwheels.controllers.login;
    exports org.team.rentwheels.controllers.mainActivity;
    exports org.team.rentwheels.controllers.brand;
}