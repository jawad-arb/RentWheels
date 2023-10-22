package org.team.rentwheels.repositories.implementations;

import org.team.rentwheels.DatabaseConnection;
import org.team.rentwheels.models.User;
import org.team.rentwheels.repositories.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.team.rentwheels.queries.UserQuery.GET_USER_BY_USERNAME_PASSWORD;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User getUserIfExist(String username, String password) throws SQLException {
        // DB Connection
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(GET_USER_BY_USERNAME_PASSWORD);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return null;
    }
}
