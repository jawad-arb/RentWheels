module org.team.rentwheels {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.team.rentwheels to javafx.fxml;
    opens org.team.rentwheels.controllers.brand to javafx.fxml;
    opens org.team.rentwheels.controllers.mainActivity to javafx.fxml;
    opens org.team.rentwheels.controllers.reservation to javafx.fxml;
    opens org.team.rentwheels.controllers.requests to javafx.fxml;
    opens org.team.rentwheels.models to javafx.base;


    exports org.team.rentwheels;

    exports org.team.rentwheels.controllers.login;
    exports org.team.rentwheels.controllers.mainActivity;
    exports org.team.rentwheels.controllers.brand;
    exports org.team.rentwheels.controllers.dashboard;
    exports org.team.rentwheels.controllers.reservation;
    exports org.team.rentwheels.controllers.cars;
    exports org.team.rentwheels.controllers.customer;
    exports org.team.rentwheels.controllers.blackList;
    exports org.team.rentwheels.controllers.requests;
    exports org.team.rentwheels.controllers.statistics;


}