package org.team.rentwheels.repositories;

import org.team.rentwheels.models.User;

import java.sql.SQLException;

public interface UserRepository {

    User getUserIfExist(String username, String password) throws SQLException;
}
