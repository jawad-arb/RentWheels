package org.team.rentwheels;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection connection;
    String dbName="RentWheels";
    String dbUserName="root";
    String dbPassword="root";
    String url="jdbc:mysql://localhost/" +dbName;

    public DatabaseConnection() {
    }

    public Connection connection() throws SQLException {
        connection = DriverManager.getConnection(url,dbUserName,dbPassword);
        return connection;
    }

    public void close() throws SQLException{
        connection.close();
    }


}
