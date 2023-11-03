package org.team.rentwheels.utils;

import org.team.rentwheels.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseOperations {
    public PreparedStatement setConnection(String query) throws SQLException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        return connection.prepareStatement(query);
    }

}
